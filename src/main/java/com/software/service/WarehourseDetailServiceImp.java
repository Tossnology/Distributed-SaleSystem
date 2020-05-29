package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.WarehourseDetailMapper;
import com.software.domain.WarehourseDetail;

@Service
public class WarehourseDetailServiceImp implements WarehourseDetailService {

	@Autowired
	private WarehourseDetailMapper wdm;
	
	@Override
	public void deleteByPrimaryKey(WarehourseDetail record) 
	{
		wdm.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(WarehourseDetail record) 
	{
		wdm.insert(record);
	}

	@Override
	public void insertSelective(WarehourseDetail record) 
	{
		wdm.insertSelective(record);
	}

	@Override
	public WarehourseDetail selectByPrimaryKey(WarehourseDetail record) 
	{
		return wdm.selectByPrimaryKey(record);
	}

	@Override
	public List<WarehourseDetail> select(WarehourseDetail record) 
	{
		return wdm.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(WarehourseDetail record) 
	{
		updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(WarehourseDetail record) 
	{
		wdm.updateByPrimaryKey(record);
	}

	@Override
	public void createNewTable(WarehourseDetail record) 
	{
		wdm.createNewTable(record.getTablename());
	}

	@Override
	public void dropTable(WarehourseDetail record) 
	{
		wdm.dropTable(record.getTablename());
	}
	
	
}
