package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.WarehourseOrderCommonMapper;
import com.software.domain.WarehourseOrderCommon;

@Service
public class WarehourseOrderCommonServiceImp implements WarehourseOrderCommonService {

	@Autowired
	private WarehourseOrderCommonMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(WarehourseOrderCommon record) 
	{
		mapper.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(WarehourseOrderCommon record) {
		mapper.insert(record);
	}

	@Override
	public void insertSelective(WarehourseOrderCommon record) {
		mapper.insertSelective(record);
	}

	@Override
	public WarehourseOrderCommon selectByPrimaryKey(WarehourseOrderCommon record) {
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<WarehourseOrderCommon> select(WarehourseOrderCommon record) {
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(WarehourseOrderCommon record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(WarehourseOrderCommon record) {
		mapper.updateByPrimaryKey(record);
	}

}
