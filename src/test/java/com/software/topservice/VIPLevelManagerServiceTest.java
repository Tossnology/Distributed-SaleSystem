package com.software.topservice;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.software.domain.VIPLevel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VIPLevelManagerServiceTest 
{
	@Autowired
	private VIPLevelManagerService service;
	
	@Test
	public void testUpdate() 
	{
		VIPLevel vip = new VIPLevel();
		vip.setId(1);
		vip.setVipname("VIP1");
		vip.setPricetopoint(+0.0f);
		vip.setPointtoprice(+0.0f);
		service.updateByPrimaryKey(vip);
	}
//	
//	@Test
//	public void testSelect() 
//	{
//		List<VIPLevel> vipLevels = service.select(null);
//		for (VIPLevel vipLevel : vipLevels) 
//		{
//			System.out.println(vipLevel);
//		}
//	}
//	
//	@Test
//	public void testMenu()
//	{
//		Map<Integer, VIPLevel> menu = service.vipMenu();
//		for (Integer index : menu.keySet()) 
//		{
//			System.out.println(index+"  "+menu.get(index));
//		}
//	}

}
