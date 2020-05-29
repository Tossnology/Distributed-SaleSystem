package com.software.topservice;

import java.util.List;
import java.util.Map;

import com.software.domain.Supplier;

public interface SupplierManagerService 
{
	public void deleteByPrimaryKey(Supplier record);

    public void insert(Supplier record);

    public void insertSelective(Supplier record);

    public Supplier selectByPrimaryKey(Supplier record);

    public List<Supplier> select(Supplier record);
    
    public void updateByPrimaryKeySelective(Supplier record);

    public void updateByPrimaryKey(Supplier record);
    
    public Map<Integer, String> supplierMenu();
}
