package com.software.topservice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.software.domain.Item;
import com.software.domain.SaleorderItem;
import com.software.service.SaleorderItemService;
import com.software.trans.ReceiveCargo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemManagerSerivceTest 
{
	@Autowired
	private ItemManagerSerivce service;
	
	@Autowired
	private SaleorderItemService saleorderService;
	
	@Test
	public void testInsert() 
	{
//		ReceiveCargo cargo = new ReceiveCargo();
//		cargo.setId("");
//		cargo.setTablename("-1");
//		cargo.setName("java22");
//		cargo.setPurchaseprice("2.0");
//		cargo.setWholesaleprice("4");
//		cargo.setRetailprice("4.0");
//		cargo.setType("wujinlei");
//		cargo.setSpecification("nothing at all");
//		cargo.setLabel("valid");
//		cargo.setTime("sj");
//		
//		service.insertSelective(cargo);
	}

	@Test
	public void testUpdate()
	{
//		ReceiveCargo cargo = new ReceiveCargo();
//		cargo.setId("4");
//		cargo.setTablename("-1");
//		cargo.setName("java111");
//		cargo.setPurchaseprice("3.0");
//		cargo.setWholesaleprice("");
//		cargo.setRetailprice("");
//		cargo.setType("wujinlei");
//		cargo.setSpecification("nothing at all");
//		cargo.setLabel("valid");
//		cargo.setTime("sj");
//		
//		service.updateByPrimaryKeySelective(cargo);
	}
	
	@Test
	public void testDelete()
	{
//		ReceiveCargo cargo = new ReceiveCargo();
//		cargo.setId("8");
//		cargo.setTablename("-1");
//		cargo.setName("java111");
//		cargo.setPurchaseprice("3.0");
//		cargo.setWholesaleprice("");
//		cargo.setRetailprice("");
//		cargo.setType("wujinlei");
//		cargo.setSpecification("nothing at all");
//		cargo.setLabel("invalid");
//		cargo.setTime("sj");
//		
//		System.out.println(service.deleteByPrimaryKey(cargo));
	}

	@Test
	public void testforcited()
	{
		SaleorderItem exampleItem = new SaleorderItem();
		exampleItem.setTablename("sub_saleorder_item_0014");
		exampleItem.setItemid(8);
		if (saleorderService.select(exampleItem).size()==0) 
		{
			System.out.println("false");
		}
		else
		{
			System.out.println("true");
		}
	}
}
