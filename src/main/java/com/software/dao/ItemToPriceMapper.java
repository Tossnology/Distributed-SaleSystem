package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.ItemToPrice;


@Mapper
public interface ItemToPriceMapper 
{
    int deleteByPrimaryKey(ItemToPrice record);

    int insert(ItemToPrice record);

    int insertSelective(ItemToPrice record);

    ItemToPrice selectByPrimaryKey(ItemToPrice record);

    List<ItemToPrice> select(ItemToPrice record);

    int updateByPrimaryKeySelective(ItemToPrice record);

    int updateByPrimaryKey(ItemToPrice record);
    
    int createNewTable(String tablename);
    
    int dropTable(String tablename);
}