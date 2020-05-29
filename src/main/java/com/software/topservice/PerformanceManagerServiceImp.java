package com.software.topservice;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.PingTarget;
import com.software.domain.SaleorderCommon;
import com.software.domain.Staff;
import com.software.domain.SubBranchDetailMap;
import com.software.service.SaleorderCommonService;
import com.software.service.StaffService;
import com.software.service.StoreManagerService;
import com.software.service.SubBranchDetailMapService;
import com.software.trans.WarehoursePerformance;

@Service
public class PerformanceManagerServiceImp implements PerformanceManagerService 
{
	@Autowired
	private SaleorderCommonService commonService;
	
	@Autowired
	private StaffService staffService;

	@Autowired
	private SubBranchDetailMapService mapService;
	
	@Override
	public WarehoursePerformance calWarehoursePerformance(WarehoursePerformance record) 
	{
		// 设置时间
		String startTimeString = record.getStarttime();
		String endTimeString = record.getEndtime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		if (startTimeString.equals("")) 
		{
			startTimeString = "2019-01-01 00:00:00";
		}
		if (endTimeString.equals("")) 
		{
			endTimeString = df.format(new Date());
		}
		Instant startTime = dateToInstant(startTimeString);
		Instant endTime = dateToInstant(endTimeString);
		Instant orderTime;
		
		// 读取仓库所有已付款未退货的订单数目
		SaleorderCommon exampleCommon = new SaleorderCommon();
		String commontablename = "sub_saleorder_common_"+String.format("%04d", Integer.valueOf(record.getWarehourseid()));
		exampleCommon.setTablename(commontablename);
		exampleCommon.setStatus(5);
		List<SaleorderCommon> commonList = commonService.select(exampleCommon);
		
		// 读取客户数目，订单数，利润, 总销售金额
		Set<Integer> clientIDSet = new HashSet<Integer>();
		float sumMargin = 1.0f;
		float tempMargin;
		float orderNum = 0.0f;
		float sumPrice = 0.0f; 
		for (SaleorderCommon saleorderCommon : commonList) 
		{
			orderTime = dateToInstant(saleorderCommon.getGathertime());
			if (orderTime.isBefore(endTime)&&orderTime.isAfter(startTime)) 
			{
				orderNum += 1;
				clientIDSet.add(saleorderCommon.getClientid());
				tempMargin = saleorderCommon.getMargin();
				if (tempMargin<=0) 
				{
					tempMargin = 0.0f;
				}
				sumMargin += tempMargin;
				sumPrice += saleorderCommon.getSumprice();
			}
			else
			{
				continue;
			}
		}
		// 计算业绩
		float clientNum = clientIDSet.size();
		float performance = (clientNum+orderNum+1)*sumMargin-1;
		
		WarehoursePerformance warehoursePerformance = new WarehoursePerformance();
		warehoursePerformance.setWarehourseid(record.getWarehourseid());
		warehoursePerformance.setWarehoursename(record.getWarehoursename());
		warehoursePerformance.setPrincipalid(record.getPrincipalid());
		warehoursePerformance.setPrincipalname(record.getPrincipalname());
		warehoursePerformance.setPerformancedetail(String.format("%.2f", performance));
		warehoursePerformance.setClientnum(String.valueOf(clientNum));
		warehoursePerformance.setOrderNum(String.valueOf(orderNum));
		warehoursePerformance.setSumPrice(String.format("%.2f", sumPrice));
		warehoursePerformance.setStarttime(instantToDate(startTime));
		warehoursePerformance.setEndtime(instantToDate(endTime));
		return warehoursePerformance;
	}

	@Override
	public List<WarehoursePerformance> calStaffPerformance(WarehoursePerformance record) 
	{
		// 设置时间
		String startTimeString = record.getStarttime();
		String endTimeString = record.getEndtime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		if (startTimeString.equals("")) 
		{
			startTimeString = "2019-01-01 00:00:00";
		}
		if (endTimeString.equals("")) 
		{
			endTimeString = df.format(new Date());
		}
		Instant startTime = dateToInstant(startTimeString);
		Instant endTime = dateToInstant(endTimeString);
		Instant orderTime;
		
		// 统计每一个员工客户数目，订单数目，与利润
		Map<String, HashSet<Integer>> idToClienNum = new HashMap<String, HashSet<Integer>>();
		Map<String, Integer> idToOrderNum = new HashMap<String, Integer>();
		Map<String, Float> idToMargin = new HashMap<String, Float>();
		Map<String, Float> idToSumPrice = new HashMap<String, Float>();
		Map<String, WarehoursePerformance> idToPerformance = new HashMap<String, WarehoursePerformance>();
		WarehoursePerformance tempPerformance;
		
		// 结果表
		List<WarehoursePerformance> resultPerformance = new ArrayList<WarehoursePerformance>();
		
		// 初始化
		// 预防出现员工没有业绩的情况
		if (record.getPrincipalid().equals("")) 
		{
			// 查所有，初始化所有员工和店长
			initAll(idToPerformance,record.getWarehourseid(), record.getWarehoursename(), 
					instantToDate(startTime), instantToDate(endTime));
		}
		else if (record.getPrincipalid().startsWith("2")) 
		{
			
			initStoreManager(idToPerformance, record.getWarehourseid(), record.getWarehoursename(), 
					record.getPrincipalid(), instantToDate(startTime), instantToDate(endTime));
		}
		else if (record.getPrincipalid().startsWith("3")) 
		{
			// 查员工
			initStaff(idToPerformance, record.getWarehourseid(), record.getWarehoursename(), 
					record.getPrincipalid(), instantToDate(startTime), instantToDate(endTime));
		}
		else
		{
			// 非法ID
			return resultPerformance;
		}
		
		if (idToPerformance.keySet().size()==0) 
		{
			// 初始化失败，不存在对应的业务员
			return resultPerformance;
		}
		
		// 遍历所有所有订单
		SaleorderCommon exampleCommon = new SaleorderCommon();
		String commontablename = "sub_saleorder_common_"+String.format("%04d", Integer.valueOf(record.getWarehourseid()));
		exampleCommon.setPrincipalid(record.getPrincipalid());
		exampleCommon.setTablename(commontablename);
		exampleCommon.setStatus(5);
		List<SaleorderCommon> commonList = commonService.select(exampleCommon);
	
		String principalid;
		for (SaleorderCommon saleorderCommon : commonList) 
		{
			principalid = saleorderCommon.getPrincipalid();
			// 初始化
			if (!idToClienNum.containsKey(principalid)) 
			{
				idToClienNum.put(principalid, new HashSet<Integer>());
			}
			if (!idToOrderNum.containsKey(principalid)) 
			{
				idToOrderNum.put(principalid, 0);
			}
			if (!idToMargin.containsKey(principalid)) 
			{
				idToMargin.put(principalid, 1.0f);
			}
			if (!idToSumPrice.containsKey(principalid)) 
			{
				idToSumPrice.put(principalid, 0.0f);
			}
			orderTime = dateToInstant(saleorderCommon.getGathertime());
			if (orderTime.isBefore(endTime)&&orderTime.isAfter(startTime)) 
			{
				idToClienNum.get(principalid).add(saleorderCommon.getClientid());
				idToOrderNum.put(principalid, idToOrderNum.get(principalid)+1);
				if (saleorderCommon.getMargin()>0) 
				{
					idToMargin.put(principalid, idToMargin.get(principalid)+saleorderCommon.getMargin());
				}
				idToSumPrice.put(principalid, idToSumPrice.get(principalid)+saleorderCommon.getSumprice());
			}
		}
		
		// 计算业绩
		float clientNum;
		float orderNum;
		float sumMargin;
		float sumPrice;
		float performance;
		for (String index : idToClienNum.keySet()) 
		{
			tempPerformance = idToPerformance.get(index);
			clientNum = idToClienNum.get(index).size();
			orderNum = idToOrderNum.get(index);
			sumMargin = idToMargin.get(index);
			performance = (clientNum+orderNum+1)*sumMargin-1;
			sumPrice = idToSumPrice.get(index);
			
			tempPerformance.setClientnum(String.valueOf(clientNum));
			tempPerformance.setOrderNum(String.valueOf(orderNum));
			tempPerformance.setPerformancedetail(String.format("%.2f", performance));
			tempPerformance.setSumPrice(String.format("%.2f", sumPrice));
		}
		
		for (String index : idToPerformance.keySet()) 
		{
			resultPerformance.add(idToPerformance.get(index));
		}
		return resultPerformance;
	}
	
	private Instant dateToInstant(String date)
	{
		String temp = date.replace(" ", "T");
		temp = temp+"Z";
		return Instant.parse(temp);
	}
	
	private String instantToDate(Instant instant)
	{
		String temp = instant.toString();
		return temp.replace("T", " ").substring(0, temp.length()-1);
	}
	
	private void initAll(Map<String, WarehoursePerformance> idToPerformance, 
			String warehourseid, String warehoursename, String startTime, String endTime)
	{
		WarehoursePerformance tempPerformance;
		// 为所有员工设置初始值
		Staff exampleStaff = new Staff();
		String tablename = "sub_staff_"+String.format("%04d", Integer.valueOf(warehourseid));
		exampleStaff.setTablename(tablename);
		List<Staff> staffList = staffService.select(exampleStaff);
		for (Staff staff : staffList) 
		{
			// 初始化基本信息
			tempPerformance = new WarehoursePerformance();
			
			// 仓库信息得从参数获取，不想多查一次表
			tempPerformance.setWarehourseid(warehourseid);
			tempPerformance.setWarehoursename(warehoursename);
			
			tempPerformance.setPrincipalid(staff.getId());
			tempPerformance.setPrincipalname(staff.getName());
			
			tempPerformance.setClientnum("0");
			tempPerformance.setOrderNum("0");
			tempPerformance.setPerformancedetail("0.0");
			tempPerformance.setSumPrice("0");
			tempPerformance.setStarttime(startTime);
			tempPerformance.setEndtime(endTime);
			
			idToPerformance.put(staff.getId(), tempPerformance);
		}
		
		// 为店长初始化
		SubBranchDetailMap exampleMap = new SubBranchDetailMap();
		exampleMap.setWarehourseid(Integer.valueOf(warehourseid));
		SubBranchDetailMap resultMap = mapService.select(exampleMap).get(0);
					
		tempPerformance = new WarehoursePerformance();
		
		// 仓库信息得从参数获取，不想多查一次表
		tempPerformance.setWarehourseid(warehourseid);
		tempPerformance.setWarehoursename(warehoursename);
		tempPerformance.setPrincipalid(resultMap.getPrincipalid());
		tempPerformance.setPrincipalname(resultMap.getPrincipalname());
		tempPerformance.setClientnum("0");
		tempPerformance.setOrderNum("0");
		tempPerformance.setPerformancedetail("0.0");
		tempPerformance.setSumPrice("0");
		tempPerformance.setStarttime(startTime);
		tempPerformance.setEndtime(endTime);
		idToPerformance.put(resultMap.getPrincipalid(), tempPerformance);
	}
	
	// 初始化一个员工
	public void initStaff(Map<String, WarehoursePerformance> idToPerformance,
			String warehourseid, String warehoursename, String principalid, String startTime, String endTime)
	{
		try 
		{
			WarehoursePerformance tempPerformance = new WarehoursePerformance();
			Staff exampleStaff = new Staff();
			String tablename = "sub_staff_"+String.format("%04d", Integer.valueOf(warehourseid));
			exampleStaff.setTablename(tablename);
			exampleStaff.setLabel("valid");
			exampleStaff.setId(principalid);
			
			Staff resultStaff = staffService.selectByPrimaryKey(exampleStaff);
			
			tempPerformance.setWarehourseid(warehourseid);
			tempPerformance.setWarehoursename(warehoursename);
			
			tempPerformance.setPrincipalid(resultStaff.getId());
			tempPerformance.setPrincipalname(resultStaff.getName());
			
			tempPerformance.setClientnum("0");
			tempPerformance.setOrderNum("0");
			tempPerformance.setPerformancedetail("0.0");
			tempPerformance.setSumPrice("0");

			tempPerformance.setStarttime(startTime);
			tempPerformance.setEndtime(endTime);
			
			idToPerformance.put(resultStaff.getId(), tempPerformance);
		} 
		catch (Exception e) 
		{
			System.out.println("debug 店员查不到");
			return;
		}
 	}
	
	public void initStoreManager(Map<String, WarehoursePerformance> idToPerformance,
			String warehourseid, String warehoursename, String principalid, String startTime, String endTime)
	{
		try 
		{
			SubBranchDetailMap exampleMap = new SubBranchDetailMap();
			exampleMap.setPrincipalid(principalid);
			exampleMap.setWarehourseid(Integer.valueOf(warehourseid));
			exampleMap.setLabel("valid");
			System.out.println(exampleMap);
			SubBranchDetailMap resultMap = mapService.select(exampleMap).get(0);
			
			WarehoursePerformance tempPerformance = new WarehoursePerformance();

			tempPerformance.setWarehourseid(warehourseid);
			tempPerformance.setWarehoursename(warehoursename);
			
			tempPerformance.setPrincipalid(resultMap.getPrincipalid());
			tempPerformance.setPrincipalname(resultMap.getPrincipalname());
			
			tempPerformance.setClientnum("0");
			tempPerformance.setOrderNum("0");
			tempPerformance.setPerformancedetail("0.0");
			tempPerformance.setSumPrice("0");

			tempPerformance.setStarttime(startTime);
			tempPerformance.setEndtime(endTime);
			
			idToPerformance.put(resultMap.getPrincipalid(), tempPerformance);
		} 
		catch (Exception e) 
		{
			System.out.println("debug 店长查不到");
			return;
		}
	}
}
