package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.software.domain.SaleorderCommon;
import com.software.domain.SaleorderItem;


@Mapper
public interface SaleorderItemMapper 
{
    int deleteByPrimaryKey(SaleorderItem record);

    int deleteByID(SaleorderItem record);

    int insert(SaleorderItem record);

    int insertSelective(SaleorderItem record);

    SaleorderItem selectByPrimaryKey(SaleorderItem record);

    List<SaleorderItem> select(SaleorderItem record);
    
    int updateByPrimaryKeySelective(SaleorderItem record);

    int updateByPrimaryKey(SaleorderItem record);

    int createNewTable(String tablename);
    
    int dropTable(String tablename);
}