package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.SubBranchDetailMapMapper;
import com.software.domain.SubBranchDetailMap;



@Service
public class SubBranchDetailMapServiceImp implements SubBranchDetailMapService 
{

	@Autowired
	private SubBranchDetailMapMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(SubBranchDetailMap record) 
	{
		mapper.deleteByPrimaryKey(record);
	}
	
	@Override
	public void delete(SubBranchDetailMap record) 
	{
		mapper.delete(record);
	}

	@Override
	public void insert(SubBranchDetailMap record) {
		mapper.insert(record);
	}

	@Override
	public void insertSelective(SubBranchDetailMap record) {
		mapper.insertSelective(record);
	}

	@Override
	public SubBranchDetailMap selectByPrimaryKey(SubBranchDetailMap record) {
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<SubBranchDetailMap> select(SubBranchDetailMap record) {
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(SubBranchDetailMap record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(SubBranchDetailMap record) {
		mapper.updateByPrimaryKey(record);
	}

	@Override
	public void updateByHourseID(SubBranchDetailMap record) 
	{
		mapper.updateByHourseID(record);
	}
}
