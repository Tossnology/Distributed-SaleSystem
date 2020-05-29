package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.VIPLevelMapper;
import com.software.domain.VIPLevel;

@Service
public class VIPLevelServiceImp implements VIPLevelService 
{
	@Autowired
	private VIPLevelMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(VIPLevel record) {
		mapper.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(VIPLevel record) {
		mapper.insert(record);
	}

	@Override
	public void insertSelective(VIPLevel record) {
		mapper.insertSelective(record);
	}

	@Override
	public VIPLevel selectByPrimaryKey(VIPLevel record) {
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<VIPLevel> select(VIPLevel record) {
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(VIPLevel record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(VIPLevel record) {
		mapper.updateByPrimaryKey(record);
	}

}
