package com.software.topservice;

import java.util.List;
import java.util.Map;

import com.software.domain.Warehourse;

public interface WarehourseManagerService 
{
	public String deleteByPrimaryKey(Warehourse record);
    
	public void insertSelective(Warehourse record);

	public Warehourse selectByPrimaryKey(Warehourse record);

    public List<Warehourse> select(Warehourse record);
    
    public void updateByPrimaryKeySelective(Warehourse record);
    
    public Map<Integer, String> typeMenu();
}
