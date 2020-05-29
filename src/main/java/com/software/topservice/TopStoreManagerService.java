package com.software.topservice;

import java.util.List;

import com.software.domain.StoreManager;

public interface TopStoreManagerService 
{

    public void insertSelective(StoreManager record);

    public List<StoreManager> select(StoreManager record);

    public void updateByPrimaryKeySelective(StoreManager record);
    
    public String assign(StoreManager record);
    
	public String disassign(StoreManager record);
    
    public int count();
}
