package com.software.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.software.domain.Client;

@Mapper
public interface ClientMapper 
{
	int deleteByPrimaryKey(Client record);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Client record);
    
    List<Client> select(Client record);
    
    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);
}