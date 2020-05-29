package com.software.service;

import java.util.List;

import com.software.domain.Warehourse;

public interface WarehourseService 
{
	public void deleteByPrimaryKey(Warehourse record);
	
	public void insert(Warehourse record);
	
	public void insertSelective(Warehourse record);
	
	public Warehourse selectByPrimaryKey(Warehourse record);
	
	public List<Warehourse> select(Warehourse record);
	
	public void updateByPrimaryKeySelective(Warehourse record);
	
	public void updateByPrimaryKey(Warehourse record);
}
