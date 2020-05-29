package com.software.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.domain.StoreManager;
import com.software.topservice.TopStoreManagerService;

@RestController
@RequestMapping("/shopmanager")
public class ShopmanagerController 
{
	@Autowired
	private TopStoreManagerService service;
	
	@RequestMapping("/queryById")
	public StoreManager queryManagerById(@RequestBody Map<String, String> param){
		String id = param.get("id");
		String password = "123";
		String name = param.get("name");
		String gender = param.get("gender");
		String phone = param.get("phone");
		String email = param.get("email");
		String hourseid = param.get("hourseid");
		
		StoreManager storemanager = new StoreManager();
		storemanager.setId(id);
		storemanager.setPassword(password);
		storemanager.setName(name);
		storemanager.setGender(gender);
		storemanager.setPhone(phone);
		storemanager.setEmail(email);
		storemanager.setLabel("valid");
		storemanager.setHourseid(hourseid);
		List<StoreManager> list = service.select(storemanager);
		if (list==null) 
		{
			return null;
		}
		StoreManager result = list.get(0);
		return result;
	}
	
	@RequestMapping("/query")
	public List<StoreManager> queryManager(@RequestBody Map<String, String> param){
		String id = param.get("id");
		String password = "123";
		String name = param.get("name");
		String gender = param.get("gender");
		String phone = param.get("phone");
		String email = param.get("email");
		String hourseid = param.get("hourseid");
		
		StoreManager storemanager = new StoreManager();
		storemanager.setId(id);
		storemanager.setPassword(password);
		storemanager.setName(name);
		storemanager.setGender(gender);
		storemanager.setPhone(phone);
		storemanager.setEmail(email);
		storemanager.setLabel("valid");
		storemanager.setHourseid(hourseid);

		
		List<StoreManager> result = service.select(storemanager);
		return result;
	}
	
	@RequestMapping("/insert")
	public Map<String, String> insertManager(@RequestBody Map<String, String> param)
	{
		String hourseid = param.get("hourseid");
		String mid = "0001";
		String prefix = "2";
		
		String last = String.format("%04d", service.count()+1);
		String id = prefix+mid+last;
		String password = "123";
		String name = param.get("name");
		String gender = param.get("gender");
		String phone = param.get("phone");
		String email = param.get("email");
		
		StoreManager storemanager = new StoreManager();
		storemanager.setId(id);
		storemanager.setPassword(password);
		storemanager.setName(name);
		storemanager.setGender(gender);
		storemanager.setPhone(phone);
		storemanager.setEmail(email);
		storemanager.setLabel("valid");
		storemanager.setHourseid(hourseid);
		
		service.insertSelective(storemanager);
		
		String infovalue;
		if(!hourseid.equals(""))
		{
			infovalue = service.assign(storemanager);
		}else {
			infovalue = "添加成功";
		}
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", infovalue);
		return result;
	
	}
	
	@RequestMapping("/delete")
	public String deleteManager(@RequestBody Map<String, String> param){
		String id = param.get("id");
		String password = "123";
		String name = param.get("name");
		String gender = param.get("gender");
		String phone = param.get("phone");
		String email = param.get("email");
		String hourseid = param.get("hourseid");
		System.out.println(id);
		StoreManager storemanager = new StoreManager();
		storemanager.setId(id);
		storemanager.setPassword(password);
		storemanager.setName(name);
		storemanager.setGender(gender);
		storemanager.setPhone(phone);
		storemanager.setEmail(email);
		storemanager.setLabel("invalid");
		storemanager.setHourseid(hourseid);
		
		System.out.println("i am here");
		service.updateByPrimaryKeySelective(storemanager);
		service.disassign(storemanager);
		return "success";
	}
	
	@RequestMapping("/update")
	public Map<String, String> updateManager(@RequestBody Map<String, String> param){
		String id = param.get("id");
		String password = "123";
		String name = param.get("name");
		String gender = param.get("gender");
		String phone = param.get("phone");
		String email = param.get("email");
		String hourseid = param.get("hourseid");
		
		StoreManager storemanager = new StoreManager();
		storemanager.setId(id);
		storemanager.setPassword(password);
		storemanager.setName(name);
		storemanager.setGender(gender);
		storemanager.setPhone(phone);
		storemanager.setEmail(email);
		storemanager.setLabel("valid");
		storemanager.setHourseid(hourseid);
		
		String infovalue;
		service.updateByPrimaryKeySelective(storemanager);
		if(hourseid.equals(""))
		{
			infovalue = service.disassign(storemanager);
		}
		else
		{
			infovalue = service.assign(storemanager);
		}
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", infovalue);
		return result;
	}
	
	@RequestMapping("/queryWare")
	public String queryWareIdByManagerId(@RequestBody Map<String, String> param){
		String id = param.get("id");
		StoreManager storemanager = new StoreManager();
		storemanager.setId(id);
		
		StoreManager result = null;
		return result.getHourseid();
	}
	
	@RequestMapping("/assign")
	public Map<String, String> assign(@RequestBody Map<String, String> param)
	{
		String id = param.get("id");
		String name = param.get("name");
		String hourseid = param.get("hourseid");
		
		StoreManager storemanager = new StoreManager();
		storemanager.setId(id);
		storemanager.setName(name);
		storemanager.setLabel("valid");
		storemanager.setHourseid(hourseid);
		String infovalue;
		if(hourseid.equals(""))
		{
			infovalue = service.disassign(storemanager);
		}
		else
		{
			infovalue = service.assign(storemanager);
		}
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", infovalue);
		return result;
	}
}
