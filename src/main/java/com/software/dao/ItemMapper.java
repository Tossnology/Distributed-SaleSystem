package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.Item;


@Mapper
public interface ItemMapper 
{
    int deleteByPrimaryKey(Item record);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Item record);
    
    List<Item> select(Item record);
    
    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}