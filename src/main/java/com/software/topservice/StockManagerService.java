package com.software.topservice;

import java.util.List;

import com.software.trans.Stock;

public interface StockManagerService 
{
	public List<Stock> select(Stock record);
	
	public void update(List<Stock> record);
}
