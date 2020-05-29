package com.software.service;

import java.util.List;

import com.software.domain.WarehourseOrderCommon;

public interface WarehourseOrderCommonService 
{
	public void deleteByPrimaryKey(WarehourseOrderCommon record);

    public void insert(WarehourseOrderCommon record);

    public void insertSelective(WarehourseOrderCommon record);

    public WarehourseOrderCommon selectByPrimaryKey(WarehourseOrderCommon record);

    public List<WarehourseOrderCommon> select(WarehourseOrderCommon record);

    public void updateByPrimaryKeySelective(WarehourseOrderCommon record);

    public void updateByPrimaryKey(WarehourseOrderCommon record);
}
