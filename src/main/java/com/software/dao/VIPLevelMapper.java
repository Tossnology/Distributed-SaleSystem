package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.software.domain.VIPLevel;

@Mapper
public interface VIPLevelMapper 
{
    int deleteByPrimaryKey(VIPLevel record);

    int insert(VIPLevel record);

    int insertSelective(VIPLevel record);

    VIPLevel selectByPrimaryKey(VIPLevel record);
    
    List<VIPLevel> select(VIPLevel record);
    
    int updateByPrimaryKeySelective(VIPLevel record);

    int updateByPrimaryKey(VIPLevel record);
}