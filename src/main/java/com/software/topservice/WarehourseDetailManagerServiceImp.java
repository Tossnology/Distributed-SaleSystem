//package com.software.topservice;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.javassist.expr.NewArray;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.software.domain.Item;
//import com.software.domain.ItemDetail;
//import com.software.domain.SubBranchDetailMap;
//import com.software.domain.Warehourse;
//import com.software.domain.WarehourseDetail;
//import com.software.service.ItemService;
//import com.software.service.SubBranchDetailMapService;
//import com.software.service.WarehourseDetailService;
//
//@Service
//public class WarehourseDetailManagerServiceImp implements WarehourseDetailManagerService {
//
//	@Autowired
//	private ItemService itemService;
//	
//	@Autowired
//	private WarehourseDetailService detailService;
//	
//	@Autowired
//	private SubBranchDetailMapService mapService;
//	
//	
//	@Override
//	public List<ItemDetail> select(ItemDetail record) 
//	{
//		List<Item> itemList = new ArrayList<>();
//		List<WarehourseDetail> detailList = new ArrayList<>();
//		List<ItemDetail> result = new ArrayList<>();
//		String itemtablename; 
//		String detailtablename;
//		String comeFromName;
//		String comeFromID;
//		if (record.getComeFromName().equals("任意")) 
//		{
//			//查总仓库
//			itemtablename = "base_warehourse_item"; 
//			detailtablename = "base_warehourse_detail";
//			comeFromID = null;
//			comeFromName = "总仓库";
//			record.setItemtablename(itemtablename);
//			record.setDetailtablename(detailtablename);
//			record.setComeFromID(comeFromID);
//			record.setComeFromName(comeFromName);
//			result.addAll(getItemDetail(record));
//			
//			//查子仓库
//			SubBranchDetailMap exampleBranch = new SubBranchDetailMap();
//			exampleBranch.setLabel("valid");
//			List<SubBranchDetailMap> branches = mapService.select(exampleBranch);
//			for (SubBranchDetailMap subBranchDetailMap : branches) 
//			{
//				itemtablename = subBranchDetailMap.getItemtable(); 
//				detailtablename = subBranchDetailMap.getWarehoursedetailtable();
//				comeFromID = subBranchDetailMap.getWarehourseid()+"";
//				comeFromName = subBranchDetailMap.getWarehoursename();
//				record.setItemtablename(itemtablename);
//				record.setDetailtablename(detailtablename);
//				record.setComeFromID(comeFromID);
//				record.setComeFromName(comeFromName);
//				result.addAll(getItemDetail(record));
//			}
//			return result;
//		}
//		else if(record.getComeFromName().equals("总仓库"))
//		{
//			itemtablename = "base_warehourse_item"; 
//			detailtablename = "base_warehourse_detail";
//			comeFromID = null;
//			comeFromName = "总仓库";
//			record.setItemtablename(itemtablename);
//			record.setDetailtablename(detailtablename);
//			record.setComeFromID(comeFromID);
//			record.setComeFromName(comeFromName);
//			return getItemDetail(record);
//		}
//		// 选择其中一个
//		else
//		{
//			SubBranchDetailMap exampleBranch = new SubBranchDetailMap();
//			exampleBranch.setWarehoursename(record.getComeFromName());
//			exampleBranch.setLabel("valid");
//			// 如果出错，避讳有逻辑错误
//			SubBranchDetailMap branchResult = mapService.select(exampleBranch).get(0);
//			itemtablename = branchResult.getItemtable(); 
//			detailtablename = branchResult.getWarehoursedetailtable();
//			comeFromID = branchResult.getWarehourseid()+"";
//			comeFromName = branchResult.getWarehoursename();
//			record.setItemtablename(itemtablename);
//			record.setDetailtablename(detailtablename);
//			record.setComeFromID(comeFromID);
//			record.setComeFromName(comeFromName);
//			return getItemDetail(record); 
//		}
//	}
//	
//	private List<ItemDetail> getItemDetail(ItemDetail record)
//	{
//		List<Item> itemList = new ArrayList<>();
//		List<WarehourseDetail> detailList = new ArrayList<>();
//		List<ItemDetail> result = new ArrayList<>();
//		
//		Item exampleItem = record.toItem();
//		WarehourseDetail exampleDetail = record.toWarehourseDetail();
//		itemList = itemService.select(exampleItem);
//		detailList = detailService.select(exampleDetail);
//		Map<Integer, ItemDetail> maps = new HashMap<Integer, ItemDetail>();
//		ItemDetail tempItemDetail;
//		//两步初始化
//		for (Item item : itemList) 
//		{
//			tempItemDetail = new ItemDetail();
//			tempItemDetail.partyInitByItem(item);
//			tempItemDetail.setItemtablename(record.getItemtablename());
//			maps.put(item.getId(), tempItemDetail);
//		}
//		for (WarehourseDetail warehourseDetail : detailList) 
//		{
//			tempItemDetail = maps.get(warehourseDetail.getItemid());
//			tempItemDetail.setDetailtablename(record.getDetailtablename());
//			tempItemDetail.partyInitByDetail(warehourseDetail);
//		}
//		for (Integer index : maps.keySet()) 
//		{
//			result.add(maps.get(index));
//		}
//		return result;
//	}
//	
//	@Override
//	public void updateByPrimaryKeySelective(ItemDetail record) 
//	{
//		WarehourseDetail detail = record.toWarehourseDetail();
//		String detailTableName = "sub_warehourse_item_"+String.format("%04d", Integer.valueOf(record.getComeFromID()));
//		detail.setTablename(detailTableName);
//		detailService.updateByPrimaryKeySelective(detail);
//	}
//
//	@Override
//	public List<String> typeMenu() 
//	{
//		List<Item> reuslt = itemService.select(null);
//		List<String> typeList = new ArrayList<String>();
//		for (Item item : reuslt) 
//		{
//			typeList.add(item.getType());
//		}
//		return typeList;
//	}
//
//}
