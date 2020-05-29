package com.software.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.domain.Staff;
import com.software.domain.Warehourse;
import com.software.service.WarehourseService;
import com.software.topservice.WarehourseManagerService;


@RestController
@RequestMapping("/warehourse")
public class WarehourseController 
{
	@Autowired
	private WarehourseManagerService service;
	
	@RequestMapping("/queryById")
	public Warehourse queryWarehourseById(@RequestBody Map<String, String> param){
		Warehourse warehourse = new Warehourse();
		warehourse.setId(Integer.valueOf(param.get("id")));
		warehourse.setName(param.get("name"));
		warehourse.setLocation(param.get("location"));
		warehourse.setLabel("valid");
		Warehourse result = service.selectByPrimaryKey(warehourse);
		return result;
	}
	
	@RequestMapping("/query")
	public List<Warehourse> queryWarehourse(@RequestBody Map<String, String> param){
		Warehourse warehourse = new Warehourse();
		if (!(param.get("id").equals(""))) {
			warehourse.setId(Integer.valueOf(param.get("id")));
		}
		warehourse.setName(param.get("name"));
		warehourse.setLocation(param.get("location"));
		warehourse.setLabel("valid");
		List<Warehourse> result = service.select(warehourse);
		return result;
	}
	
	@RequestMapping("/add")
	public Map<String, String> addWarehourse(@RequestBody Map<String, String> param){
		Warehourse warehourse = new Warehourse();
		if (!(param.get("id").equals(""))) {
			warehourse.setId(Integer.valueOf(param.get("id")));
		}
		warehourse.setName(param.get("name"));
		warehourse.setLocation(param.get("location"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		warehourse.setTime(date);
		warehourse.setLabel("valid");
		service.insertSelective(warehourse);
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", "添加成功");
		return result;
	}
	
	@RequestMapping("/delete")
	public Map<String, String> deleteWarehourse(@RequestBody Map<String, String> param){
		Warehourse warehourse = new Warehourse();
		if (!(param.get("id").equals(""))) {
			warehourse.setId(Integer.valueOf(param.get("id")));
		}
		warehourse.setName(param.get("name"));
		warehourse.setLocation(param.get("location"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		warehourse.setTime(date);
		warehourse.setLabel("invalid");
		service.deleteByPrimaryKey(warehourse);
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", "删除成功");
		return result;
	}
	
	@RequestMapping("/update")
	public Map<String, String> updateWarehourse(@RequestBody Map<String, String> param){
		Warehourse warehourse = new Warehourse();
		if (!(param.get("id").equals(""))) {
			warehourse.setId(Integer.valueOf(param.get("id")));
		}
		warehourse.setName(param.get("name"));
		warehourse.setLocation(param.get("location"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		warehourse.setTime(date);
		warehourse.setLabel("valid");
		service.updateByPrimaryKeySelective(warehourse);
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", "修改成功");
		return result;
	}
	
	@RequestMapping("/typemenu")
	public Map<Integer, String> typeMenu()
	{
		return service.typeMenu();
	}
}
