package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.WarehourseOrderCommon;


@Mapper
public interface WarehourseOrderCommonMapper 
{
    int deleteByPrimaryKey(WarehourseOrderCommon record);

    int insert(WarehourseOrderCommon record);

    int insertSelective(WarehourseOrderCommon record);

    WarehourseOrderCommon selectByPrimaryKey(WarehourseOrderCommon record);

    List<WarehourseOrderCommon> select(WarehourseOrderCommon record);

    int updateByPrimaryKeySelective(WarehourseOrderCommon record);

    int updateByPrimaryKey(WarehourseOrderCommon record);
}