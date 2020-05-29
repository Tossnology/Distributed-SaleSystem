package com.software.dao;

import static org.junit.Assert.*;

import javax.swing.Spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.software.domain.Staff;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaffMapperTest 
{
	@Autowired
	private StaffMapper mapper;

	@Test
	public void test() 
	{
		Staff staff = new Staff();
		staff.setTablename("sub_staff_0001");
		staff.setId("300010001");
		Staff resultStaff = mapper.selectByPrimaryKey(staff);
		System.out.println(resultStaff.getEmail());
	}

}
