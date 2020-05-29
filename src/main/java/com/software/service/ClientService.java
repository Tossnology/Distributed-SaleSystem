package com.software.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.software.domain.Client;

public interface ClientService 
{
	public void deleteByPrimaryKey(Client record);

    public void insert(Client record);

    public void insertSelective(Client record);

    public Client selectByPrimaryKey(Client record);

    public List<Client> select(Client record);

    public void updateByPrimaryKeySelective(Client record);

    public void updateByPrimaryKey(Client record);
}
