package com.software.topservice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.software.trans.ReceiveWarehourseOrder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehourseOrderManagerServiceTest 
{

	@Autowired
	private WarehourseOrderManagerService service;
	
	@Test
	public void testInsert() 
	{
//		ReceiveWarehourseOrder order1 = new ReceiveWarehourseOrder();
//		order1.setSourceid("-2");
//		order1.setSourcename("经销商");
//		order1.setTargetid("-1");
//		order1.setTargetname("总仓库");
//		order1.setPrincipalid("200010001");
//		order1.setPrincipalname("java");
//		order1.setType("经->总");
//		order1.setStatus("1");
//		order1.setOrdersumprice("20");
//		
//		order1.setItemid("1");
//		order1.setItemname("sufei");
//		order1.setItemnum("10");
//		order1.setPerprice("2");
//		order1.setSumprice("10");
//		
//		ReceiveWarehourseOrder order2 = new ReceiveWarehourseOrder();
//		order1.setSourceid("-2");
//		order1.setSourcename("经销商");
//		order1.setTargetid("-1");
//		order1.setTargetname("总仓库");
//		order1.setPrincipalid("200010001");
//		order1.setPrincipalname("java");
//		order1.setType("经->总");
//		order1.setStatus("1");
//		order1.setOrdersumprice("20");
//		
//		order2.setItemid("2");
//		order2.setItemname("苏菲");
//		order2.setItemnum("10");
//		order2.setPerprice("2");
//		order2.setSumprice("10");
//		
//		List<ReceiveWarehourseOrder> orderList = new ArrayList<ReceiveWarehourseOrder>();
//		orderList.add(order1);
//		orderList.add(order2);
//		service.insert(orderList);
		
	}
	
	@Test
	public void testUpdate()
	{
//		ReceiveWarehourseOrder order1 = new ReceiveWarehourseOrder();
//		order1.setId("1");
//		order1.setSourceid("-1");
//		order1.setSourcename("总仓库");
//		order1.setTargetid("1");
//		order1.setTargetname("1号仓库");
//		order1.setPrincipalid("200010001");
//		order1.setPrincipalname("java");
//		order1.setType("总->子111");
//		order1.setStatus("1");
//		order1.setOrdersumprice("20");
//		
//		order1.setItemid("1");
//		order1.setItemname("sufei1");
//		order1.setItemnum("10");
//		order1.setPerprice("2");
//		order1.setSumprice("10");
//		
//		ReceiveWarehourseOrder order2 = new ReceiveWarehourseOrder();
//		order2.setId("1");		
//		order2.setSourceid("-1");
//		order2.setSourcename("总仓库");
//		order2.setTargetid("1");
//		order2.setTargetname("1号仓库");
//		order2.setPrincipalid("200010001");
//		order2.setPrincipalname("java");
//		order2.setType("总->子");
//		order2.setStatus("1");
//		order2.setOrdersumprice("20");
//		
//		order2.setItemid("2");
//		order2.setItemname("苏菲1");
//		order2.setItemnum("10");
//		order2.setPerprice("2");
//		order2.setSumprice("10");
//		
//		List<ReceiveWarehourseOrder> orderList = new ArrayList<ReceiveWarehourseOrder>();
//		orderList.add(order1);
//		orderList.add(order2);
//		service.update(orderList);
	}

	@Test
	public void testCheck()
	{
		ReceiveWarehourseOrder order1 = new ReceiveWarehourseOrder();
		order1.setId(8+"");
		order1.setSourceid("-2");
		order1.setSourcename("经销商");
		order1.setTargetid("-1");
		order1.setTargetname("总仓库");
		order1.setPrincipalid("200010001");
		order1.setPrincipalname("java");
		order1.setType("1");
		order1.setStatus("4");
		order1.setOrdersumprice("20");
		order1.setChecktime("2019-12-12 12:32:15");
		service.checkOrder(order1);
		
	}
}
