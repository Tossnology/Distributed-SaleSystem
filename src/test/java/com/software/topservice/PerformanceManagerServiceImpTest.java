package com.software.topservice;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.software.trans.WarehoursePerformance;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceManagerServiceImpTest 
{
	@Autowired
	private PerformanceManagerService service;
	
	@Test
	public void testWarehoursePerformance() 
	{
//		WarehoursePerformance examplePerformance = new WarehoursePerformance();
//		examplePerformance.setWarehourseid("1");
//		examplePerformance.setWarehoursename("warehoursename");
//		examplePerformance.setPrincipalid("200010001");
//		examplePerformance.setPrincipalname("java");
//		examplePerformance.setStarttime("2019-12-15 00:00:01");
//		examplePerformance.setEndtime("2019-10-02 00:00:00");
//		System.out.println(service.calWarehoursePerformance(examplePerformance));
	}
	
	@Test
	public void testStaffPerformance1() 
	{
//		WarehoursePerformance examplePerformance = new WarehoursePerformance();
//		examplePerformance.setWarehourseid("1");
//		examplePerformance.setWarehoursename("warehoursename");
//		examplePerformance.setPrincipalid("");
//		examplePerformance.setStarttime("");
//		examplePerformance.setEndtime("");
//		for (WarehoursePerformance performance : service.calStaffPerformance(examplePerformance)) 
//		{
//			System.out.println(performance);
//		}
	}
	
	@Test
	public void testStaffPerformance2() 
	{
		WarehoursePerformance examplePerformance = new WarehoursePerformance();
		examplePerformance.setWarehourseid("1");
		examplePerformance.setWarehoursename("warehoursename");
		examplePerformance.setPrincipalid("300010003");
		examplePerformance.setStarttime("");
		examplePerformance.setEndtime("");
		for (WarehoursePerformance performance : service.calStaffPerformance(examplePerformance)) 
		{
			System.out.println(performance);
		}
	}
	

}
