package com.software.service;

import java.util.List;

import com.software.domain.Staff;

public interface StaffService 
{
	public void deleteByPrimaryKey(Staff record);

    public void insert(Staff record);

    public void insertSelective(Staff record);

    public Staff selectByPrimaryKey(Staff record);

    public List<Staff> select(Staff record);

    public void updateByPrimaryKeySelective(Staff record);

    public void updateByPrimaryKey(Staff record);
    
    public void createNewTable(Staff record);
    
    public void dropTable(Staff record);
    
    public int count(Staff record);
}
