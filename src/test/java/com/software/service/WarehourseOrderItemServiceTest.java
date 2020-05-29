package com.software.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.software.domain.WarehourseOrderItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehourseOrderItemServiceTest {

	@Autowired
	private WarehourseOrderItemService service;
	
	@Test
	public void test() 
	{
		WarehourseOrderItem item = new WarehourseOrderItem();
		item.setId("1");
		service.deleteByID(item);
	}

}
