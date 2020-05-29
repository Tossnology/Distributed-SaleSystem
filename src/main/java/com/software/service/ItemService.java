package com.software.service;

import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.software.domain.Item;

public interface ItemService 
{
	public void deleteByPrimaryKey(Item record);

	public void insert(Item record);

    public void insertSelective(Item record);

    public Item selectByPrimaryKey(Item record);
    
    public List<Item> select(Item record);
    
    public void updateByPrimaryKeySelective(Item record);

    public void updateByPrimaryKey(Item record);
}
