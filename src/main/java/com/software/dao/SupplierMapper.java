package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.software.domain.Supplier;

@Mapper
public interface SupplierMapper 
{
    int deleteByPrimaryKey(Supplier record);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Supplier record);

    List<Supplier> select(Supplier record);
    
    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}