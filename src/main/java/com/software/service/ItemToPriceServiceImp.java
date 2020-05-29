package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.ItemToPriceMapper;
import com.software.domain.ItemToPrice;

@Service
public class ItemToPriceServiceImp implements ItemToPriceService {

	@Autowired
	private ItemToPriceMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(ItemToPrice record) 
	{
		mapper.deleteByPrimaryKey(record);

	}

	@Override
	public void insert(ItemToPrice record) 
	{
		mapper.insert(record);
	}

	@Override
	public void insertSelective(ItemToPrice record) {
		mapper.insertSelective(record);
	}

	@Override
	public ItemToPrice selectByPrimaryKey(ItemToPrice record) {
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<ItemToPrice> select(ItemToPrice record) {
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(ItemToPrice record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(ItemToPrice record) {
		mapper.updateByPrimaryKey(record);
	}

	@Override
	public void createNewTable(ItemToPrice record) 
	{
		mapper.createNewTable(record.getTablename());
	}

	@Override
	public void dropTable(ItemToPrice record) 
	{
		mapper.dropTable(record.getTablename());
	}

}
