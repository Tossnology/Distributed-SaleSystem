package com.software.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.software.domain.SaleorderCommon;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleorderCommonServiceImpTest 
{
	@Autowired
	private SaleorderCommonService service;
	
	@Test
	public void test() 
	{
		SaleorderCommon common = new SaleorderCommon();
		common.setTablename("sub_saleorder_common_0001");
		common.setClientid(0);
		for (SaleorderCommon example : service.select(common)) 
		{
			System.out.println(example);
		}
	}

}
