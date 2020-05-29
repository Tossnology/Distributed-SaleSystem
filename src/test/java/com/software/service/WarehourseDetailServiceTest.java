package com.software.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.software.domain.WarehourseDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehourseDetailServiceTest 
{
	@Autowired
	private WarehourseDetailService service;
	@Test
	public void test() 
	{
		WarehourseDetail detail = new WarehourseDetail();
		detail.setTablename("base_warehourse_detail");
		detail.setItemid(1);
		detail.setItemnum(11);
		detail.setTime("sj");
		service.updateByPrimaryKey(detail);
	}

}
