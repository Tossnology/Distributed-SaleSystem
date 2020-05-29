package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.SaleorderCommon;

import com.software.domain.SaleorderCommon;

@Mapper
public interface SaleorderCommonMapper {


    int deleteByPrimaryKey(SaleorderCommon record);

    int insert(SaleorderCommon record);

    int insertSelective(SaleorderCommon record);

    SaleorderCommon selectByPrimaryKey(SaleorderCommon record);

    List<SaleorderCommon> select(SaleorderCommon record);

    int updateByPrimaryKeySelective(SaleorderCommon record);

    int updateByPrimaryKey(SaleorderCommon record);
    
    int createNewTable(String tablename);
    
    int dropTable(String tablename);

}