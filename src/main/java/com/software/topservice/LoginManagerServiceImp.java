package com.software.topservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.domain.GeneralManager;
import com.software.domain.Staff;
import com.software.domain.StoreManager;
import com.software.domain.SubBranchDetailMap;
import com.software.service.GeneralManagerService;
import com.software.service.StaffService;
import com.software.service.StoreManagerService;
import com.software.service.SubBranchDetailMapService;

@Service
public class LoginManagerServiceImp implements LoginManagerService 
{

	@Autowired
	private GeneralManagerService managerService;
	
	@Autowired
	private StoreManagerService storeService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private SubBranchDetailMapService branchService;
	
	@Override
	public SubBranchDetailMap login(String id, String password, String authority) 
	{
		if (authority.equals("general-manager")) 
		{
			return generalManagerLogin(id, password);
		}
		else if (authority.equals("shop-manager")) 
		{
			return storeManagerLogin(id, password);
		}
		else
		{
			return staffLogin(id, password);
		}
	}
	
	private SubBranchDetailMap generalManagerLogin(String id, String password)
	{
		SubBranchDetailMap result = new SubBranchDetailMap();
		
		GeneralManager exampleManager = new GeneralManager();
		exampleManager.setId(id);
		GeneralManager manager = managerService.selectByPrimaryKey(exampleManager);
		if (manager==null) 
		{
			result.setFlag("wrongid");
		}
		else if(!manager.getPassword().equals(password))
		{
			result.setFlag("wrongpwd");
		}
		else
		{
			result.setWarehourseid(-1);
			result.setWarehoursename("总仓库");
			result.setWarehourselocation("");
			result.setPrincipalid(id);
			result.setPrincipalname(manager.getName());
			result.setItemtable("base_warehourse_itemtoprice");
			result.setWarehoursedetailtable("base_warehourse_detail");
			result.setSaleordercommontable("");
			result.setSaleorderitemtable("");
			result.setFlag("true");
		}
		return result;
	}
	
	private SubBranchDetailMap storeManagerLogin(String id, String password)
	{
		SubBranchDetailMap result = new SubBranchDetailMap();
		StoreManager exampleManager = new StoreManager();
		exampleManager.setId(id);
		exampleManager.setLabel("valid");
		StoreManager manager = storeService.selectByPrimaryKey(exampleManager);
		if (manager==null) 
		{
			result.setFlag("wrongid");
		}
		else if(!manager.getPassword().equals(password))
		{
			result.setFlag("wrongpwd");
		}
		else
		{
			SubBranchDetailMap exampleBranch = new SubBranchDetailMap();
			exampleBranch.setPrincipalid(id);
			exampleBranch.setPrincipalname(manager.getName());
			exampleBranch.setLabel("valid");
			SubBranchDetailMap temp = getAuthority(exampleBranch);
			if (temp==null) 
			{
				result.setFlag("wrongid");
			}
			else 
			{
				result = temp;
				result.setFlag("true");
			}
		}
		return result;
	}
	
	private SubBranchDetailMap staffLogin(String id, String password)
	{
		SubBranchDetailMap result = new SubBranchDetailMap();
		Staff exampleStaff = new Staff();
		exampleStaff.setId(id);
		exampleStaff.setLabel("valid");
		String hourseid = id.substring(1, 5);
		exampleStaff.setTablename("sub_staff_"+hourseid);
		Staff validStaff = null;
		try 
		{
			List<Staff> list = staffService.select(exampleStaff);
			if(list.size()==0)
			{
				result.setFlag("wrongid");
				return result;
			}
			else
			{
				validStaff = list.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("查表失败");
			validStaff = null;
		}
		if (validStaff==null) 
		{
			result.setFlag("wrongid");
		}
		else if (!validStaff.getPassword().equals(password)) 
		{
			result.setFlag("wrongpwd");
		}
		else
		{
			
			result.setLabel("valid");
			result.setWarehourseid(Integer.valueOf(hourseid));
			SubBranchDetailMap temp = getAuthority(result);
			if (temp==null) 
			{
				System.out.println("无权限");
				result.setFlag("wrongid");
			}
			else 
			{
				result = temp;
				result.setPrincipalid(id);
				result.setPrincipalname(validStaff.getName());
				result.setFlag("true");
			}
		}
		return result;
	}
	
	private SubBranchDetailMap getAuthority(SubBranchDetailMap exampleBranch)
	{
		List<SubBranchDetailMap> branches = branchService.select(exampleBranch);
		if (branches.isEmpty()) 
		{
			return null;
		}
		else
		{
			return branches.get(0);
		}
	}
}
