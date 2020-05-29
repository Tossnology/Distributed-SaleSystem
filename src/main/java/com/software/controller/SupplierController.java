package com.software.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.domain.Supplier;
import com.software.topservice.SupplierManagerService;
import com.software.topservice.SupplierManagerServiceImp;

@RestController
@RequestMapping("/provider")
public class SupplierController {
	
	@Autowired
	private SupplierManagerService service;
	
	@RequestMapping("/queryById")
	public Supplier queryProviderById(@RequestBody Map<String, String> param){
		Supplier receive = new Supplier();
		initReceive(receive, param);
		receive.setLabel("valid");
		
		Supplier result = service.selectByPrimaryKey(receive);
		
		return result;
	}
	
	@RequestMapping("/query")
	public List<Supplier> queryProvider(@RequestBody Map<String, String> param){
		Supplier receive = new Supplier();
		initReceive(receive, param);
		receive.setLabel("valid");
		
		List<Supplier> result = service.select(receive);
		
		return result;
	}
	
	@RequestMapping("/insert")
	public Map<String, String> insertProvider(@RequestBody Map<String, String> param){
		Supplier receive = new Supplier();
		initReceive(receive, param);
		receive.setLabel("valid");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		receive.setTime(time);
		
		service.insert(receive);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "添加成功");
		return result;
	}
	
	@RequestMapping("/delete")
	public Map<String, String> deleteProvider(@RequestBody Map<String, String> param){
		Supplier receive = new Supplier();
		initReceive(receive, param);
		receive.setLabel("invalid");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		receive.setTime(time);
		
		service.deleteByPrimaryKey(receive);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "删除成功");
		return result;
	}
	
	@RequestMapping("/update")
	public Map<String, String> updateProvider(@RequestBody Map<String, String> param){
		Supplier receive = new Supplier();
		initReceive(receive, param);
		receive.setLabel("valid");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		receive.setTime(time);
		
		service.updateByPrimaryKey(receive);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "修改成功");
		return result;
	}
	
	@RequestMapping("/providermenu")
	public Map<Integer, String> providerMenu()
	{
		return service.supplierMenu();
	}
	
	
	private void initReceive(Supplier receive,Map<String, String> param){
		receive.setAccount(param.get("account"));
		receive.setAddress(param.get("address"));
		receive.setName(param.get("name"));
		receive.setPrincipalname(param.get("principalname"));
		receive.setTime(param.get("time"));
		receive.setLabel("valid");
		if(!param.get("id").equals("")){
			receive.setId(Integer.valueOf(param.get("id")));
		}
	}
}
