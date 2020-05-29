package com.software.service;

import java.util.List;

import com.software.domain.SubBranchDetailMap;

public interface SubBranchDetailMapService 
{
	public void deleteByPrimaryKey(SubBranchDetailMap record);

	public void delete(SubBranchDetailMap record);

    public void insert(SubBranchDetailMap record);

    public void insertSelective(SubBranchDetailMap record);

    public SubBranchDetailMap selectByPrimaryKey(SubBranchDetailMap record);

    public List<SubBranchDetailMap> select(SubBranchDetailMap record);
  
    public void updateByPrimaryKeySelective(SubBranchDetailMap record);

    public void updateByPrimaryKey(SubBranchDetailMap record);
    
    public void updateByHourseID(SubBranchDetailMap record);
}
