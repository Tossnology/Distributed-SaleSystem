package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dao.SaleorderCommonMapper;
import com.software.domain.SaleorderCommon;

@Service
public class SaleorderCommonServiceImp implements SaleorderCommonService {

	@Autowired
	private SaleorderCommonMapper mapper;
	
	@Override
	public void deleteByPrimaryKey(SaleorderCommon record) {
		mapper.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(SaleorderCommon record) {
		mapper.insert(record);
	}

	@Override
	public void insertSelective(SaleorderCommon record) {
		mapper.insertSelective(record);
	}

	@Override
	public SaleorderCommon selectByPrimaryKey(SaleorderCommon record) {
		return mapper.selectByPrimaryKey(record);
	}

	@Override
	public List<SaleorderCommon> select(SaleorderCommon record) {
		return mapper.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(SaleorderCommon record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(SaleorderCommon record) {
		mapper.updateByPrimaryKey(record);
	}

	@Override
	public void createNewTable(SaleorderCommon record) {
		mapper.createNewTable(record.getTablename());
	}

	@Override
	public void dropTable(SaleorderCommon record) 
	{
		mapper.dropTable(record.getTablename());
	}

}
