package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.software.dao.SupplierMapper;
import com.software.domain.Supplier;

@Service
public class SupplierServiceImp implements SupplierService {

	@Autowired
	private SupplierMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(Supplier record) 
	{
		mapper.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(Supplier record) 
	{
		mapper.insert(record);
	}

	@Override
	public void insertSelective(Supplier record) 
	{
		mapper.insertSelective(record);
	}

	@Override
	public Supplier selectByPrimaryKey(Supplier record) 
	{
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<Supplier> select(Supplier record)
	{
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Supplier record) 
	{
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(Supplier record)
	{
		mapper.updateByPrimaryKey(record);
	}

}
