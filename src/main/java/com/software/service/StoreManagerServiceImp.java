package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.StoreManagerMapper;
import com.software.domain.StoreManager;

@Service
public class StoreManagerServiceImp implements StoreManagerService 
{
	
	@Autowired
	private StoreManagerMapper smm;
	
	
	@Override
	public void deleteByPrimaryKey(StoreManager record) 
	{
		smm.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(StoreManager record) 
	{
		smm.insert(record);
	}

	@Override
	public void insertSelective(StoreManager record) 
	{
		smm.insertSelective(record);
	}

	@Override
	public StoreManager selectByPrimaryKey(StoreManager record) 
	{
		return smm.selectByPrimaryKey(record);
	}

	@Override
	public List<StoreManager> select(StoreManager record) 
	{
		return smm.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(StoreManager record) 
	{
		smm.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(StoreManager record) 
	{
		smm.updateByPrimaryKey(record);
	}

	@Override
	public int count() 
	{
		return smm.count(null);
	}
}
