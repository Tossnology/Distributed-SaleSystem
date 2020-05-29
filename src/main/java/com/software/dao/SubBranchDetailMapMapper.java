package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.SubBranchDetailMap;

//import com.software.domain.SubBranchDetailMap;

@Mapper
public interface SubBranchDetailMapMapper {

    int deleteByPrimaryKey(SubBranchDetailMap record);

    int delete(SubBranchDetailMap record);

    int insert(SubBranchDetailMap record);

    int insertSelective(SubBranchDetailMap record);

    SubBranchDetailMap selectByPrimaryKey(SubBranchDetailMap record);
    
    List<SubBranchDetailMap> select(SubBranchDetailMap record);
    
    int updateByPrimaryKeySelective(SubBranchDetailMap record);

    int updateByPrimaryKey(SubBranchDetailMap record);

    int updateByHourseID(SubBranchDetailMap record);
}