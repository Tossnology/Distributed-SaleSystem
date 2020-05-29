package com.software.service;

import java.util.List;

import com.software.domain.Supplier;

public interface SupplierService 
{
	public void deleteByPrimaryKey(Supplier record);

    public void insert(Supplier record);

    public void insertSelective(Supplier record);

    public Supplier selectByPrimaryKey(Supplier record);

    public List<Supplier> select(Supplier record);
    
    public void updateByPrimaryKeySelective(Supplier record);

    public void updateByPrimaryKey(Supplier record);
}
