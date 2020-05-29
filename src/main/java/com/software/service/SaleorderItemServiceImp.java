package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.SaleorderItemMapper;
import com.software.domain.SaleorderItem;

@Service
public class SaleorderItemServiceImp implements SaleorderItemService {

	@Autowired
	private SaleorderItemMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(SaleorderItem record) {
		mapper.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(SaleorderItem record) {
		mapper.insert(record);
	}

	@Override
	public void insertSelective(SaleorderItem record) {
		mapper.insertSelective(record);
	}

	@Override
	public SaleorderItem selectByPrimaryKey(SaleorderItem record) {
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<SaleorderItem> select(SaleorderItem record) {
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(SaleorderItem record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(SaleorderItem record) {
		mapper.updateByPrimaryKey(record);
	}

	@Override
	public void createNewTable(SaleorderItem record) 
	{
		mapper.createNewTable(record.getTablename());
	}

	@Override
	public void deleteByID(SaleorderItem record) 
	{
		mapper.deleteByID(record);
	}

	@Override
	public void dropTable(SaleorderItem record) 
	{
		mapper.dropTable(record.getTablename());
	}

}
