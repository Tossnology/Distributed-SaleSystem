package com.software.topservice;

import com.software.domain.SubBranchDetailMap;

public interface LoginManagerService 
{
	public SubBranchDetailMap login(String id, String password, String authority);
}
