package com.software.service;

import java.util.List;

import com.software.domain.VIPLevel;

public interface VIPLevelService 
{
	public void deleteByPrimaryKey(VIPLevel record);

    public void insert(VIPLevel record);

    public void insertSelective(VIPLevel record);

    public VIPLevel selectByPrimaryKey(VIPLevel record);
    
    public List<VIPLevel> select(VIPLevel record);
    
    public void updateByPrimaryKeySelective(VIPLevel record);

    public void updateByPrimaryKey(VIPLevel record);
}
