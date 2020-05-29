package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.Staff;


@Mapper
public interface StaffMapper 
{

    int deleteByPrimaryKey(Staff record);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(Staff record);
    
    List<Staff> select(Staff record);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
    
    int count(Staff record);
    
    int createNewTable(String tablename);
    
    int dropTable(String tablename);
}