package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.WarehourseOrderItem;


@Mapper
public interface WarehourseOrderItemMapper 
{
    int deleteByPrimaryKey(WarehourseOrderItem record);

    int deleteByID(WarehourseOrderItem record);
    
    int insert(WarehourseOrderItem record);

    int insertSelective(WarehourseOrderItem record);

    WarehourseOrderItem selectByPrimaryKey(WarehourseOrderItem record);

    List<WarehourseOrderItem> select(WarehourseOrderItem record);
    
    int updateByPrimaryKeySelective(WarehourseOrderItem record);

    int updateByPrimaryKey(WarehourseOrderItem record);
}