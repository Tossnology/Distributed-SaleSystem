package com.software.service;

import java.util.List;

import com.software.domain.StoreManager;

public interface StoreManagerService 
{
	public void deleteByPrimaryKey(StoreManager record);
	
	public void insert(StoreManager record);
	
	public void insertSelective(StoreManager record);
	
	public StoreManager selectByPrimaryKey(StoreManager record);
	
	public List<StoreManager> select(StoreManager record);
	
	public void updateByPrimaryKeySelective(StoreManager record);
	
	public void updateByPrimaryKey(StoreManager record);
	
	public int count();
}
