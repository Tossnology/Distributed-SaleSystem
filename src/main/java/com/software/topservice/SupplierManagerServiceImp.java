package com.software.topservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.domain.Supplier;
import com.software.service.SupplierService;

@Service
public class SupplierManagerServiceImp implements SupplierManagerService 
{
	@Autowired
	private SupplierService service;
	
	@Override
	public void deleteByPrimaryKey(Supplier record) 
	{
		service.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(Supplier record) 
	{
		service.insert(record);
	}

	@Override
	public void insertSelective(Supplier record) 
	{
		service.insertSelective(record);
	}

	@Override
	public Supplier selectByPrimaryKey(Supplier record) 
	{
		return service.selectByPrimaryKey(record);
	}

	@Override
	public List<Supplier> select(Supplier record) 
	{
		return service.select(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Supplier record) 
	{
		service.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(Supplier record) 
	{
		service.updateByPrimaryKey(record);
	}

	@Override
	public Map<Integer, String> supplierMenu() 
	{
		List<Supplier> list = service.select(null);
		Map<Integer, String> idToName = new HashMap<Integer, String>();
		for (Supplier supplier : list) 
		{
			idToName.put(supplier.getId(), supplier.getName());
		}
		return idToName;
	}
	
	

}
