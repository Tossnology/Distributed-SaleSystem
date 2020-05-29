package com.software.dao;

import org.apache.ibatis.annotations.Mapper;

import com.software.domain.GeneralManager;

@Mapper
public interface GeneralManagerMapper 
{
    int deleteByPrimaryKey(GeneralManager record);

    int insert(GeneralManager record);

    int insertSelective(GeneralManager record);

    GeneralManager selectByPrimaryKey(GeneralManager record);

    int updateByPrimaryKeySelective(GeneralManager record);

    int updateByPrimaryKey(GeneralManager record);
}