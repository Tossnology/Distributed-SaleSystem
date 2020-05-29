package com.software.service;

import java.util.List;

import com.software.domain.SaleorderCommon;


public interface SaleorderCommonService 
{
	public void deleteByPrimaryKey(SaleorderCommon record);

	public void insert(SaleorderCommon record);
	
	public void insertSelective(SaleorderCommon record);
	
	SaleorderCommon selectByPrimaryKey(SaleorderCommon record);
	
	List<SaleorderCommon> select(SaleorderCommon record);
	
	public void updateByPrimaryKeySelective(SaleorderCommon record);
	
	public void updateByPrimaryKey(SaleorderCommon record);
	
	public void createNewTable(SaleorderCommon record);

	public void dropTable(SaleorderCommon record);
}
