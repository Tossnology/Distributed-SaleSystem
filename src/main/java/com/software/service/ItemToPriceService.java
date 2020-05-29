package com.software.service;

import java.util.List;

import com.software.domain.ItemToPrice;

public interface ItemToPriceService 
{
	public void deleteByPrimaryKey(ItemToPrice record);

    public void insert(ItemToPrice record);

    public void insertSelective(ItemToPrice record);

    public ItemToPrice selectByPrimaryKey(ItemToPrice record);

    public List<ItemToPrice> select(ItemToPrice record);

    public void updateByPrimaryKeySelective(ItemToPrice record);

    public void updateByPrimaryKey(ItemToPrice record);
    
    public void createNewTable(ItemToPrice record);
    
    public void dropTable(ItemToPrice record);
}
