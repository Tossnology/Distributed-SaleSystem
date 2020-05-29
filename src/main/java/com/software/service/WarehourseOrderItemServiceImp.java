package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.WarehourseOrderItemMapper;
import com.software.domain.WarehourseOrderItem;

@Service
public class WarehourseOrderItemServiceImp implements WarehourseOrderItemService 
{
	@Autowired
	private WarehourseOrderItemMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(WarehourseOrderItem record) 
	{
		mapper.deleteByPrimaryKey(record);
		
	}

	@Override
	public void insert(WarehourseOrderItem record) {
		mapper.insert(record);
	}

	@Override
	public void insertSelective(WarehourseOrderItem record) {
		mapper.insert(record);		
	}

	@Override
	public WarehourseOrderItem selectByPrimaryKey(WarehourseOrderItem record) 
	{
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<WarehourseOrderItem> select(WarehourseOrderItem record) {
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(WarehourseOrderItem record) {
		mapper.updateByPrimaryKey(record);
	}

	@Override
	public void updateByPrimaryKey(WarehourseOrderItem record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByID(WarehourseOrderItem record) 
	{
		mapper.deleteByID(record);
	}
	


}
