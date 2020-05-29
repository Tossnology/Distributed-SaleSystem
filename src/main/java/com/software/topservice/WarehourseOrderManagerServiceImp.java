package com.software.topservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.domain.ItemToPrice;
import com.software.domain.SubBranchDetailMap;
import com.software.domain.WarehourseDetail;
import com.software.domain.WarehourseOrderCommon;
import com.software.domain.WarehourseOrderItem;
import com.software.service.ItemToPriceService;
import com.software.service.SubBranchDetailMapService;
import com.software.service.WarehourseDetailService;
import com.software.service.WarehourseOrderCommonService;
import com.software.service.WarehourseOrderItemService;
import com.software.trans.ReceiveWarehourseOrder;
import com.software.trans.SendWarehourseOrder;


@Service
public class WarehourseOrderManagerServiceImp implements WarehourseOrderManagerService 
{

	@Autowired
	private WarehourseOrderCommonService commonService;
	
	@Autowired
	private WarehourseOrderItemService itemService;
	
	@Autowired
	private WarehourseDetailService detailService;
	
	@Autowired
	private ItemToPriceService priceService;
	
	@Autowired
	private SubBranchDetailMapService mapService;
	
	@Override
	public List<SendWarehourseOrder> select(ReceiveWarehourseOrder order) 
	{	
		List<SendWarehourseOrder> result = new ArrayList<SendWarehourseOrder>();
		WarehourseOrderCommon examplecommon = order.toWarehourseOrderCommon();

		// 选取符合条件的订单
		List<WarehourseOrderCommon> commonList = commonService.select(examplecommon);
		
		// 获取每一条订单的基本信息
		SendWarehourseOrder orderTemp;
		WarehourseOrderItem itemTemp = new WarehourseOrderItem();
		for (WarehourseOrderCommon warehourseOrderCommon : commonList) 
		{
			orderTemp = new SendWarehourseOrder(); // 新建
			orderTemp.initByCommon(warehourseOrderCommon);
			
			//获取订单对应的物品
			itemTemp.setId(warehourseOrderCommon.getId()+"");
			orderTemp.setItems(itemService.select(itemTemp));
			result.add(orderTemp);
		}
		return result;
	}

	@Override
	public void insert(List<ReceiveWarehourseOrder> orderList) 
	{
		if (orderList.size()==0) 
		{
			System.out.println("没有商品，insert个屁");
			return;
		}
		// 插入的时候不知道订单ID
		ReceiveWarehourseOrder example = orderList.get(0);
		WarehourseOrderCommon exampleCommon = example.toWarehourseOrderCommon();
		example.setId(null);
		example.setChecktime(getRandom()+"");
		commonService.insertSelective(exampleCommon);
		
		// 查出插入后的ID
		WarehourseOrderCommon resultCommon = commonService.select(exampleCommon).get(0);
		
		// 将具体的商品信息插入到detail表里面
		WarehourseOrderItem itemTemp;
		for (ReceiveWarehourseOrder receiveWarehourseOrder : orderList) 
		{
			itemTemp = receiveWarehourseOrder.toWarehourseOrderItem();
			// 设置关联关系
			itemTemp.setId(resultCommon.getId()+"");
			itemService.insertSelective(itemTemp);
		}
	}

	@Override
	public void update(List<ReceiveWarehourseOrder> orderList) 
	{
		// 更新共同信息
		ReceiveWarehourseOrder example = orderList.get(0);
		WarehourseOrderCommon exampleCommon = example.toWarehourseOrderCommon();
		commonService.updateByPrimaryKeySelective(exampleCommon);
		
		// 删除订单中的商品
		WarehourseOrderItem exampleItem = new WarehourseOrderItem();
		exampleItem.setId(example.getId()+"");
		itemService.deleteByID(exampleItem);
		
		WarehourseOrderItem itemTemp;
		// 重新添加
		for (ReceiveWarehourseOrder receiveWarehourseOrder : orderList) 
		{
			itemTemp = receiveWarehourseOrder.toWarehourseOrderItem();
			itemTemp.setViceid(null);
			itemService.insertSelective(itemTemp);
		}
	}

	@Override
	public void delete(ReceiveWarehourseOrder order) 
	{
		WarehourseOrderCommon exampleCommon = order.toWarehourseOrderCommon();
		commonService.deleteByPrimaryKey(exampleCommon);
		
		WarehourseOrderItem exampleItem = order.toWarehourseOrderItem();
		itemService.deleteByID(exampleItem);
		
	}

	@Override
	public String checkOrder(ReceiveWarehourseOrder order) 
	{
		// common 最后才更新
		WarehourseOrderCommon exampleCommon = order.toWarehourseOrderCommon();
		String date = exampleCommon.getChecktime();
		WarehourseOrderCommon resultCommon = commonService.selectByPrimaryKey(exampleCommon);
		resultCommon.setChecktime(date);
		resultCommon.setStatus(exampleCommon.getStatus());
		
		// 获取订单的商品
		WarehourseOrderItem exampleItem = order.toWarehourseOrderItem();
		exampleItem.setViceid(null);
		List<WarehourseOrderItem> itemList = itemService.select(exampleItem);
		
		// 用于初始化商品数量信息表，获取源和目的表名
		String sourceTableName = null;
		String targetTableName;

		if (!order.getType().equals("1")) {
			sourceTableName = getWarehourseDetailTable(resultCommon.getSourceid());
		}
		targetTableName = getWarehourseDetailTable(resultCommon.getTargetid());
		
		// 用于保存变化后商品数量信息
		List<WarehourseDetail> sourceItemList = new ArrayList<>();
		List<WarehourseDetail> targetUpdateItemList = new ArrayList<>();
		
		WarehourseDetail exampleDetail = new WarehourseDetail();
		WarehourseDetail sourceDetail;
		WarehourseDetail targetDetail;
		
		// 遍历订单中所有货品
		// 子->总、、子-总 作为一个类  源要减，目的加     
		// 进货商->总设为一个类        目的加   
		
		for (WarehourseOrderItem warehourseOrderItem : itemList) 
		{
			if (sourceTableName!=null) 
			{
				exampleDetail.setTablename(sourceTableName);
				exampleDetail.setItemid(warehourseOrderItem.getItemid());
				
				sourceDetail = detailService.selectByPrimaryKey(exampleDetail);
				if (sourceDetail==null) 
				{
					// 源仓库中没有该商品， 需要先添加   如果出现这一步，则会有逻辑错误
					System.out.println("逻辑错误");
					return "false";
				}
				if (sourceDetail.getItemnum()<warehourseOrderItem.getItemnum()) 
				{
					// 商品数量不够，审核失败
					return "商品数量不够，审核失败";
				}
				// 源仓库，减
				sourceDetail.setTablename(sourceTableName);
				sourceDetail.setItemnum(sourceDetail.getItemnum()-warehourseOrderItem.getItemnum());
				sourceDetail.setTime(date);  // 更新时间
				sourceItemList.add(sourceDetail);
			}
			// 目的仓库加
			exampleDetail.setTablename(targetTableName);
			exampleDetail.setItemid(warehourseOrderItem.getItemid());
			targetDetail = detailService.selectByPrimaryKey(exampleDetail);
		
			targetDetail.setTablename(targetTableName);
			targetDetail.setItemnum(targetDetail.getItemnum()+warehourseOrderItem.getItemnum());
			targetDetail.setTime(date);
			targetUpdateItemList.add(targetDetail);
			
		}
		//更新到仓库商品数量信息表里面
		for (WarehourseDetail warehourseDetail : sourceItemList) 
		{
			detailService.updateByPrimaryKey(warehourseDetail);
		}
		for (WarehourseDetail warehourseDetail : targetUpdateItemList) 
		{
			detailService.updateByPrimaryKey(warehourseDetail);
		}
		
		// 初始化所有仓库的进货价 
		if (resultCommon.getType().equals("1")) 
		{
			System.out.println("i am here");
			initPurchasePrice(order);
		}
		commonService.updateByPrimaryKeySelective(resultCommon);
		return "审核成功";
	}
	
	private String getWarehourseDetailTable(Integer hourseid)
	{
		if (hourseid == -1) 
		{
			return "base_warehourse_detail";
		}
		else if(hourseid == -2)
		{
			return null;
		}
		else
		{
			return "sub_warehourse_detail_"+String.format("%04d", hourseid);
		}
		
	}

	private void initPurchasePrice(ReceiveWarehourseOrder order)
	{
		WarehourseOrderItem exampleItem = new WarehourseOrderItem();
		exampleItem.setId(order.getId());
		List<WarehourseOrderItem> itemList = itemService.select(exampleItem);
	
		// 初始化总仓库与子仓库进货价信息
		SubBranchDetailMap generalMap = new SubBranchDetailMap();
		generalMap.setItemtable("base_warehourse_itemtoprice");
		SubBranchDetailMap exampleMap = new SubBranchDetailMap();
		exampleMap.setLabel("valid");
		List<SubBranchDetailMap> mapList = mapService.select(exampleMap);
		mapList.add(generalMap);
		ItemToPrice price = new ItemToPrice();
		for (SubBranchDetailMap subBranchDetailMap : mapList) 
		{
			for (WarehourseOrderItem warehourseOrderItem : itemList) 
			{
				price.setTablename(subBranchDetailMap.getItemtable());
				price.setId(warehourseOrderItem.getItemid());
				price.setName(warehourseOrderItem.getItemname());
				price.setPurchaseprice(warehourseOrderItem.getPerprice());
				price.setTime(order.getChecktime());
				price.setLabel("valid");
				priceService.updateByPrimaryKeySelective(price);
			}
		}
	}
	
	private int getRandom()
	{
		Random rand = new Random();
		return rand.nextInt(100000000);
	}

	@Override
	public void updateStatus(ReceiveWarehourseOrder order) 
	{
		WarehourseOrderCommon exampleCommon = order.toWarehourseOrderCommon();
		commonService.updateByPrimaryKeySelective(exampleCommon);
	}

	@Override
	public Map<String, String> inoutMoney(Integer warehourseid) 
	{
		WarehourseOrderCommon example = new WarehourseOrderCommon();
		float inMoney = 0;
		float outMoney = 0;
		if (warehourseid==-1) 
		{
			// 总仓库
			example.setTargetid(warehourseid);
			example.setType("1");
			inMoney = calSumPrice(example);
			
			example.setTargetid(null);
			example.setType("2");
			example.setSourceid(warehourseid);
			outMoney = calSumPrice(example);
		}
		else
		{
			// 总仓库
			example.setTargetid(warehourseid);
			example.setType("2");
			inMoney = calSumPrice(example);
			
			example.setTargetid(null);
			example.setType("2");
			example.setSourceid(warehourseid);
			outMoney = calSumPrice(example);
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("in", String.format("%.2f", inMoney));
		result.put("out", String.format("%.2f", outMoney));
		return result;
	}
	
	private float calSumPrice(WarehourseOrderCommon record)
	{
		float sumPrice = 0;
		for (WarehourseOrderCommon common : commonService.select(record)) {
			sumPrice += common.getSumprice();
		}
		return sumPrice;
	}
}
