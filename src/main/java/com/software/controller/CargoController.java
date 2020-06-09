package com.software.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.topservice.ItemManagerSerivce;
import com.software.trans.ReceiveCargo;
import com.software.trans.SendCargo2Andr;

@Slf4j
@RestController
@RequestMapping("/cargo")
public class CargoController 
{
	
	@Autowired
	private ItemManagerSerivce service;
	@RequestMapping("/queryById")
	public ReceiveCargo queryCargoById(@RequestBody ReceiveCargo param)
	{
		System.out.println("hhh"+param.toString());
		param.setLabel("valid");
		ReceiveCargo result = service.selectByPrimaryKey(param);
		log.info(LoginController.currentUserId+" "+"queryCargo : "+param);
		return result;
	}
	
	@RequestMapping("/query")
	public List<ReceiveCargo> queryCargo(@RequestBody ReceiveCargo param)
	{
//		System.out.println("ID    "+param.getId());
//		System.out.println("hourseID"+ param.getTablename());
		System.out.println(param);
		param.setLabel("valid");
		List<ReceiveCargo> result = service.select(param);
		System.out.println(result.size());
//		for(ReceiveCargo r:result){
//			System.out.println(r.toString());
//		}
		log.info(LoginController.currentUserId+" "+"queryCargo : "+param);
		return result;
	}
	
	@RequestMapping("/add")
	public Map<String, String> addCargo(@RequestBody ReceiveCargo param){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		
		param.setLabel("valid");
		param.setTime(date);
		service.insertSelective(param);
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", "添加成功");
		log.info(LoginController.currentUserId+" "+"addCargo : "+param);
		return result;
	}
	
	@RequestMapping("/delete")
	public Map<String, String> deleteCargo(@RequestBody ReceiveCargo param)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		param.setTime(date);
		param.setLabel("invalid");
		
		String infovalue = service.deleteByPrimaryKey(param);
		System.out.println(infovalue);
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", infovalue);
		log.info(LoginController.currentUserId+" "+"deleteCargo : "+param);
		return result;
	}
	
	@RequestMapping("/update")
	public Map<String, String> updateCargo(@RequestBody ReceiveCargo param){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		param.setTime(date);
		param.setLabel("valid");
		service.updateByPrimaryKeySelective(param);
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", "更新成功");
		log.info(LoginController.currentUserId+" "+"updateCargo : "+param);
		return result;
	}
	
	@RequestMapping("/query2")
	public SendCargo2Andr queryCargo2(@RequestBody ReceiveCargo param)
	{
//		System.out.println("ID    "+param.getId());
//		System.out.println("hourseID"+ param.getTablename());
		System.out.println(param);
		param.setLabel("valid");
		List<ReceiveCargo> list = service.select(param);
		SendCargo2Andr result = new SendCargo2Andr(list);
//		System.out.println(result.size());
		for(ReceiveCargo r:list){
			System.out.println(r.toString());
		}
		return result;
	}
	
	public Map<Integer, String> typeMenu()
	{
		return service.typeMenu();
	}
}
