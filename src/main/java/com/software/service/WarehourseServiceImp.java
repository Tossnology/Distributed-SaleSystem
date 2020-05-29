package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.WarehourseMapper;
import com.software.domain.Warehourse;

@Service
public class WarehourseServiceImp implements WarehourseService {

	@Autowired
	private WarehourseMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(Warehourse record) 
	{
		mapper.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(Warehourse record) 
	{
		mapper.insert(record);
	}

	@Override
	public void insertSelective(Warehourse record) {
		mapper.insertSelective(record);
	}

	@Override
	public Warehourse selectByPrimaryKey(Warehourse record) {
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<Warehourse> select(Warehourse record) {
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Warehourse record) 
	{
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(Warehourse record) 
	{
		mapper.updateByPrimaryKey(record);
	}

}
