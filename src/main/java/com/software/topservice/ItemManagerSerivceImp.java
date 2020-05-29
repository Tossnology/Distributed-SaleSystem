package com.software.topservice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.domain.Item;
import com.software.domain.ItemToPrice;
import com.software.domain.SaleorderItem;
import com.software.domain.SubBranchDetailMap;
import com.software.domain.WarehourseDetail;
import com.software.domain.WarehourseOrderItem;
import com.software.service.ItemService;
import com.software.service.ItemToPriceService;
import com.software.service.SaleorderItemService;
import com.software.service.SubBranchDetailMapService;
import com.software.service.WarehourseDetailService;
import com.software.service.WarehourseOrderItemService;
import com.software.trans.ReceiveCargo;

@Service
public class ItemManagerSerivceImp implements ItemManagerSerivce 
{
	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemToPriceService priceService;

	@Autowired
	private WarehourseDetailService detailService;
	
	@Autowired
	private SubBranchDetailMapService branchService;
	
	@Autowired
	private SaleorderItemService saleorderItemSerivce;
	
	@Autowired
	private WarehourseOrderItemService warehourseItemService;
	
	@Override
	public ReceiveCargo selectByPrimaryKey(ReceiveCargo record) 
	{
		String hourseid = record.getTablename();
		record.fillTablename();
		Item exampleItem = record.toItem();
		ItemToPrice examplePrice = record.toPrice();
		Item item = itemService.selectByPrimaryKey(exampleItem);
		if (item!=null) 
		{
			ReceiveCargo cargo = new ReceiveCargo();
			ItemToPrice price = priceService.selectByPrimaryKey(examplePrice);
			cargo.setTablename(hourseid);
			cargo.initByItem(item);
			cargo.initByPrice(price);
			return cargo;
		}
		else
		{
			return null;
		}

	}
	
	@Override
	public void insertSelective(ReceiveCargo record) 
	{
		record.fillTablename();
		Item exampleItem = record.toItem();
		exampleItem.setPicture(getRandom()+"");
		itemService.insertSelective(exampleItem);
		
		Item resultItem = itemService.select(exampleItem).get(0);
		
		// 给总仓库和所有子仓库的初始化
		SubBranchDetailMap exampleMap = new SubBranchDetailMap();
		exampleMap.setLabel("valid");
		List<SubBranchDetailMap> resultMaps = branchService.select(exampleMap);
		
		WarehourseDetail detail = new WarehourseDetail();
		detail.setItemid(resultItem.getId());
		detail.setItemnum(0);
		detail.setTime(record.getTime());
		detail.setTablename("base_warehourse_detail");
		detailService.insertSelective(detail);
		
		ItemToPrice examplePrice = record.toPrice();
		examplePrice.setId(resultItem.getId());
		examplePrice.setPurchaseprice(0.0f);
		examplePrice.setRetailprice(0.0f);
		examplePrice.setWholesaleprice(0.0f);
		priceService.insertSelective(examplePrice);
		
		for (SubBranchDetailMap subBranchDetailMap : resultMaps) 
		{
			detail.setTablename(subBranchDetailMap.getWarehoursedetailtable());
			detailService.insertSelective(detail);
			
			examplePrice.setTablename(subBranchDetailMap.getItemtable());
			priceService.insertSelective(examplePrice);
		}
	}

	@Override
	public List<ReceiveCargo> select(ReceiveCargo record) 
	{
		String hourseid = record.getTablename();
		record.fillTablename();
		Item exampleItem = record.toItem();
		ItemToPrice examplePrice = record.toPrice();
		
		List<Item> itemList = itemService.select(exampleItem);
		Map<Integer, ReceiveCargo> map = new HashMap<Integer, ReceiveCargo>();
		
		ReceiveCargo cargo;
		for (Item item : itemList) 
		{
			cargo = new ReceiveCargo();
			cargo.initByItem(item);
			cargo.setTablename(hourseid);
			map.put(item.getId(), cargo);
		}
		
		List<ItemToPrice> prices = priceService.select(examplePrice);
		for (ItemToPrice itemToPrice : prices) 
		{
			try 
			{
				itemToPrice.setTablename(examplePrice.getTablename());
				map.get(itemToPrice.getId()).initByPrice(itemToPrice);
			} catch (Exception e) 
			{
				continue;
			}
			
		}
		
		List<ReceiveCargo> result = new ArrayList<>();
		for (Integer index : map.keySet()) 
		{
			result.add(map.get(index));
		}
		return result;
	}

	@Override
	public void updateByPrimaryKeySelective(ReceiveCargo record) 
	{	
		record.fillTablename();
		Item exampleItem = record.toItem();
		ItemToPrice examplePrice = record.toPrice();
		
		itemService.updateByPrimaryKeySelective(exampleItem);
		priceService.updateByPrimaryKeySelective(examplePrice);
	}
	
	@Override
	public String deleteByPrimaryKey(ReceiveCargo record) 
	{
		record.fillTablename();
		Item exampleItem = record.toItem();
		
		SubBranchDetailMap exampleMap = new SubBranchDetailMap();
		exampleMap.setLabel("valid");
		List<SubBranchDetailMap> mapList = branchService.select(exampleMap);
		
		WarehourseDetail resultDetail;
		WarehourseDetail exampleDetail = new WarehourseDetail();
		
		ItemToPrice resultPrice;
		
		SubBranchDetailMap generalMap = new SubBranchDetailMap();
		generalMap.setWarehoursename("总仓库");
		generalMap.setWarehoursedetailtable("base_warehourse_detail");
		generalMap.setItemtable("base_warehourse_itemtoprice");
		mapList.add(generalMap);
		
		List<WarehourseDetail> deleteDetail = new ArrayList<>();
		List<ItemToPrice> deletePrice = new ArrayList<>();
		
		
		// 遍历 总仓库和子仓库，判断是否还存在该商品
		for (SubBranchDetailMap subBranchDetailMap : mapList) 
		{
			exampleDetail.setItemid(Integer.valueOf(record.getId()));
			exampleDetail.setTablename(subBranchDetailMap.getWarehoursedetailtable());
			resultDetail = detailService.selectByPrimaryKey(exampleDetail);
			if (resultDetail.getItemnum()>0) 
			{
				return "删除失败，"+subBranchDetailMap.getWarehoursename()+"还存在该商品";
			}
			resultDetail.setTablename(subBranchDetailMap.getWarehoursedetailtable());
			deleteDetail.add(resultDetail);
			
			resultPrice = new ItemToPrice();
			resultPrice.setId(Integer.valueOf(record.getId()));
			resultPrice.setTablename(subBranchDetailMap.getItemtable());
			deletePrice.add(resultPrice);
			
			if (!subBranchDetailMap.getWarehoursename().equals("总仓库")) 
			{
				// 看看商品是否被引用
				if (isCitedBySaleOrder(Integer.valueOf(record.getId()), subBranchDetailMap.getSaleorderitemtable())) 
				{
					return "删除失败,商品"+record.getName()+"销售单引用";
				}
				if (isCitedByWarehourseOrder(Integer.valueOf(record.getId()))) 
				{
					return "删除失败,商品"+record.getName()+"仓库单引用";
				}
			}
		}
		
		// 删除子仓库的price 和 数量信息
		for (ItemToPrice price : deletePrice) 
		{
			priceService.deleteByPrimaryKey(price);
		}
		for (WarehourseDetail detail : deleteDetail) 
		{
			detailService.deleteByPrimaryKey(detail);
		}
		itemService.updateByPrimaryKeySelective(exampleItem);
		return "删除成功";
	}
	

	@Override
	public Map<Integer, String> typeMenu() 
	{
		Item item = new Item();
		item.setLabel("valid");
		List<Item> itemList = itemService.select(item);
		Map<Integer, String> result = new HashMap<>();
		for (int i = 0; i < itemList.size(); i++) 
		{
			result.put(i, itemList.get(i).getType());
		}
		return result;
	}
	
	private int getRandom() 
	{
		Random random = new Random();
		return random.nextInt(100000000);
	}
	
	// 判断商品是否被引用
	private boolean isCitedBySaleOrder(Integer itemid, String saleorderitemtablename)
	{
		SaleorderItem exampleItem = new SaleorderItem();
		exampleItem.setTablename(saleorderitemtablename);
		exampleItem.setItemid(itemid);
		if (saleorderItemSerivce.select(exampleItem).size()==0) 
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private boolean isCitedByWarehourseOrder(Integer itemid)
	{
		WarehourseOrderItem exampleItem = new WarehourseOrderItem();
		exampleItem.setItemid(itemid);
		if (warehourseItemService.select(exampleItem).size()==0) 
		{
			return false;
		}
		else
		{
			return true;
		}
			
	}
}
