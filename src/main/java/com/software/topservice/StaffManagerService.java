package com.software.topservice;

import java.util.List;

import com.software.domain.Staff;
import com.software.domain.StoreManager;

public interface StaffManagerService 
{
    public void insertSelective(Staff record);

    public Staff selectByPrimaryKey(Staff record);

    public List<Staff> select(Staff record);

    public void updateByPrimaryKeySelective(Staff record);
    
    public int count(Staff record);
}
