package com.software.service;

import java.util.List;

import com.software.domain.WarehourseDetail;


public interface WarehourseDetailService 
{
	public void deleteByPrimaryKey(WarehourseDetail record);

    public void insert(WarehourseDetail record);

    public void insertSelective(WarehourseDetail record);

    public WarehourseDetail selectByPrimaryKey(WarehourseDetail record);
    
    public List<WarehourseDetail> select(WarehourseDetail record);
    
    public void updateByPrimaryKeySelective(WarehourseDetail record);

    public void updateByPrimaryKey(WarehourseDetail record);
    
    public void createNewTable(WarehourseDetail record);
    
    public void dropTable(WarehourseDetail record);
}
