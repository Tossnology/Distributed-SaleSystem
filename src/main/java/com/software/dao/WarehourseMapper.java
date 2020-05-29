package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.Warehourse;


@Mapper
public interface WarehourseMapper 
{
    int deleteByPrimaryKey(Warehourse record);

    int insert(Warehourse record);

    int insertSelective(Warehourse record);

    Warehourse selectByPrimaryKey(Warehourse record);

    List<Warehourse> select(Warehourse record);
    
    int updateByPrimaryKeySelective(Warehourse record);

    int updateByPrimaryKey(Warehourse record);
}