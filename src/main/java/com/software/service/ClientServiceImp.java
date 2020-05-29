package com.software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.software.dao.ClientMapper;
import com.software.domain.Client;

@Service
public class ClientServiceImp implements ClientService {

	@Autowired
	private ClientMapper cm;
	
	@Override
	public void deleteByPrimaryKey(Client record) 
	{
		cm.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(Client record) 
	{
		cm.insert(record);

	}

	@Override
	public void insertSelective(Client record) 
	{
		cm.insertSelective(record);

	}

	@Override
	public List<Client> select(Client record) 
	{
		return cm.select(record);
	}
	
	@Override
	public Client selectByPrimaryKey(Client record) 
	{
		return cm.selectByPrimaryKey(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Client record) 
	{
		cm.updateByPrimaryKeySelective(record);

	}

	@Override
	public void updateByPrimaryKey(Client record) 
	{
		cm.updateByPrimaryKey(record);
	}
}
