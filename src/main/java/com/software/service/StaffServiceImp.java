package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.StaffMapper;
import com.software.domain.Staff;

@Service
public class StaffServiceImp implements StaffService {

	@Autowired
	private StaffMapper sm;
	
	@Override
	public void deleteByPrimaryKey(Staff record) 
	{
		sm.deleteByPrimaryKey(record);
	}
	
	@Override
	public void insert(Staff record) 
	{
		sm.insert(record);
	}

	@Override
	public void insertSelective(Staff record) 
	{
		sm.insertSelective(record);
	}

	@Override
	public Staff selectByPrimaryKey(Staff record) 
	{
		return sm.selectByPrimaryKey(record);
	}

	@Override
	public List<Staff> select(Staff record) 
	{
		return sm.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Staff record) 
	{
		sm.updateByPrimaryKeySelective(record);

	}

	@Override
	public void updateByPrimaryKey(Staff record) 
	{
		sm.updateByPrimaryKey(record);
	}

	@Override
	public void createNewTable(Staff record) 
	{
		sm.createNewTable(record.getTablename());
	}

	@Override
	public int count(Staff record) 
	{
		return sm.count(record);
	}

	@Override
	public void dropTable(Staff record) 
	{
		sm.dropTable(record.getTablename());
	}

}
