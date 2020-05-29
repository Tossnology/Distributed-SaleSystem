package com.software.service;

import java.util.List;

import com.software.domain.SaleorderItem;

public interface SaleorderItemService 
{
	public void deleteByPrimaryKey(SaleorderItem record);

	public void deleteByID(SaleorderItem record);
	
    public void insert(SaleorderItem record);

    public void insertSelective(SaleorderItem record);

    public SaleorderItem selectByPrimaryKey(SaleorderItem record);

    public List<SaleorderItem> select(SaleorderItem record);
    
    public void updateByPrimaryKeySelective(SaleorderItem record);

    public void updateByPrimaryKey(SaleorderItem record);

    public void createNewTable(SaleorderItem record);
    
    public void dropTable(SaleorderItem record);
}
