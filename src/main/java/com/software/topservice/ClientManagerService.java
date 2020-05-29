package com.software.topservice;

import java.util.List;

import com.software.domain.Client;


public interface ClientManagerService 
{
	public String deleteByPrimaryKey(Client record);

    public void insert(Client record);

    public void insertSelective(Client record);

    public Client selectByPrimaryKey(Client record);

    public List<Client> select(Client record);
    
    public void updateByPrimaryKeySelective(Client record);

    public void updateByPrimaryKey(Client record);
}
