package com.software.service;

import java.util.List;

import com.software.domain.WarehourseOrderItem;

public interface WarehourseOrderItemService 
{
	public void deleteByPrimaryKey(WarehourseOrderItem record);

    public void deleteByID(WarehourseOrderItem record);
    
	public void insert(WarehourseOrderItem record);

    public void insertSelective(WarehourseOrderItem record);

    public WarehourseOrderItem selectByPrimaryKey(WarehourseOrderItem record);

    public List<WarehourseOrderItem> select(WarehourseOrderItem record);
    
    public void updateByPrimaryKeySelective(WarehourseOrderItem record);

    public void updateByPrimaryKey(WarehourseOrderItem record);
    
}
