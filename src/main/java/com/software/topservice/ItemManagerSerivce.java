package com.software.topservice;

import java.util.List;
import java.util.Map;

import com.software.domain.Item;
import com.software.trans.ReceiveCargo;

public interface ItemManagerSerivce 
{
    public void insertSelective(ReceiveCargo record);
 
    public ReceiveCargo selectByPrimaryKey(ReceiveCargo record);
   
    public List<ReceiveCargo> select(ReceiveCargo record);
    
    public void updateByPrimaryKeySelective(ReceiveCargo record);    
    
    public String deleteByPrimaryKey(ReceiveCargo record);
    
    public Map<Integer, String> typeMenu();
    
}
