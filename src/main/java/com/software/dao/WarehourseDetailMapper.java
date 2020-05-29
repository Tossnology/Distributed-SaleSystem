package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.software.domain.WarehourseDetail;

@Mapper
public interface WarehourseDetailMapper {
    int deleteByPrimaryKey(WarehourseDetail record);

    int insert(WarehourseDetail record);

    int insertSelective(WarehourseDetail record);

    WarehourseDetail selectByPrimaryKey(WarehourseDetail record);
    
    List<WarehourseDetail> select(WarehourseDetail record);
    
    int updateByPrimaryKeySelective(WarehourseDetail record);

    int updateByPrimaryKey(WarehourseDetail record);
    
    int createNewTable(String tablename);

    int dropTable(String tablename);
}