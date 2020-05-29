package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.ItemMapper;
import com.software.domain.Item;


@Service
public class ItemServiceImp implements ItemService 
{

	@Autowired
	private ItemMapper im;
	
	@Override
	public void deleteByPrimaryKey(Item record) 
	{
		im.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(Item record) 
	{
		im.insert(record);

	}

	@Override
	public void insertSelective(Item record) 
	{
		im.insertSelective(record);

	}

	@Override
	public Item selectByPrimaryKey(Item record) 
	{
		return im.selectByPrimaryKey(record);
	}

	@Override
	public List<Item> select(Item record) 
	{
		return im.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Item record) 
	{
		im.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(Item record) 
	{
		im.updateByPrimaryKey(record);
	}
}
