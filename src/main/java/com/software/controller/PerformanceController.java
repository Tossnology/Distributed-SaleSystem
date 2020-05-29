package com.software.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.domain.SubBranchDetailMap;
import com.software.domain.Warehourse;
import com.software.service.SubBranchDetailMapService;
import com.software.service.WarehourseService;
import com.software.topservice.PerformanceManagerService;
import com.software.trans.WarehoursePerformance;

@RestController
@RequestMapping("/performance")
public class PerformanceController 
{
	@Autowired
	private PerformanceManagerService performanceService;
	
	@Autowired
	private SubBranchDetailMapService mapService;
	
	// 需要warehouseid，时间，全部查，warehouseid默认传""
	@RequestMapping("/warehourseperformance")
	public List<WarehoursePerformance> queryWarehoursePerformance(@RequestBody WarehoursePerformance performance)
	{
		System.out.println(performance);
		List<WarehoursePerformance> result = new ArrayList<WarehoursePerformance>();
		SubBranchDetailMap exampleMap = new SubBranchDetailMap();
		exampleMap.setLabel("valid");
		if (performance.getWarehourseid().equals("")) 
		{
			for (SubBranchDetailMap map : mapService.select(exampleMap)) 
			{
				performance.setWarehourseid(map.getWarehourseid()+"");
				performance.setWarehoursename(map.getWarehoursename());
				performance.setPrincipalid(map.getPrincipalid());
				performance.setPrincipalname(map.getPrincipalname());
				result.add(performanceService.calWarehoursePerformance(performance));
			}
		}
		else
		{
			// 查单个
			exampleMap = new SubBranchDetailMap();
			exampleMap.setWarehourseid(Integer.valueOf(performance.getWarehourseid()));
			SubBranchDetailMap resultMap = mapService.select(exampleMap).get(0);
			performance.setWarehoursename(resultMap.getWarehoursename());
			performance.setPrincipalid(resultMap.getPrincipalid());
			performance.setPrincipalname(resultMap.getPrincipalname());
			result.add(performanceService.calWarehoursePerformance(performance));
		}
		return result;
	}
	
	// 需要warehouseid，warehousename， 时间，全部查principalid="" 
	@RequestMapping("/staffperformance")
	public List<WarehoursePerformance> queryStaffPerformance(@RequestBody WarehoursePerformance performance)
	{
		System.out.println(performance);
		return performanceService.calStaffPerformance(performance);
	}

}
