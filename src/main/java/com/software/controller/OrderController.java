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

import com.software.domain.SaleorderItem;
import com.software.topservice.SaleOrderManagerService;
import com.software.trans.ReceiveOrder;
import com.software.trans.SendOrder;

@RestController
@RequestMapping("/order")
public class OrderController 
{
	@Autowired
	private	SaleOrderManagerService service;
	@RequestMapping("/query3")
	public List<String> queryOrder3()
	{
		Map<String,List<String>> result2 = new HashMap<String,List<String>>();
		ReceiveOrder r = new ReceiveOrder();
		r.setWarehourseid("15");
		r.setPrincipalid("200010003");
		r.setOrderid("");
		r.setClientid("");
		r.setStatus("");
		r.setSumprice("");
		r.setGather("");
		r.setChange("");
		r.setMargin("");
		List<SendOrder> result = service.select(r);
		List<String> strings=new ArrayList<String>() ;
		for(SendOrder i:result){
			strings.add(i.getCreatetime());
		}
		System.out.println("query3 : "+strings);
		//result2.put("info", strings);
		return strings;//返回查找结果
	}
	@RequestMapping("/query4")
	public List<SaleorderItem> queryOrder4(@RequestBody Map<String, Integer> param)
	{
		//System.out.println(param);
		int x = param.get("x");
		
		ReceiveOrder r = new ReceiveOrder();
		r.setWarehourseid("15");
		r.setPrincipalid("200010003");
		r.setOrderid("");
		r.setClientid("");
		r.setStatus("");
		r.setSumprice("");
		r.setGather("");
		r.setChange("");
		r.setMargin("");
		
		List<SendOrder> result = service.select(r);
		System.out.println(result.get(x));
		return result.get(x).getItems();//返回查找结果
	}
	
	
	@RequestMapping("/query")
	public List<SendOrder> queryOrder(@RequestBody ReceiveOrder param)
	{
		System.out.println(param.toString());
		List<SendOrder> result = service.select(param);
		return result;//返回查找结果
	}
	
	@RequestMapping("/insert")
	public Map<String,String> insertOrder(@RequestBody List<ReceiveOrder> param)
	{
		if (param.size()==0) 
		{
			return null;
		}
		System.out.println(param.get(0).toString());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		param.get(0).setCreatetime(date);
		if (param.get(0).getStatus().equals("5")) 
		{
			param.get(0).setChecktime(date);
			param.get(0).setGathertime(date);
			param.get(0).setPostime(date);
		}
		String infovalue = service.insert(param);
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", infovalue);
		return result;//返回成功/失败信息
	}
	
	@RequestMapping("/update")
	public Map<String,String> updateOrder(@RequestBody List<ReceiveOrder> param)
	{
		
		String infovalue = service.update(param);
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", infovalue);
		return result;//返回成功/失败信息
	}
	
	@RequestMapping("/delete")
	public Map<String,String> deleteOrder(@RequestBody ReceiveOrder param){
		service.delete(param);
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", "删除成功");
		return result;//返回成功/失败信息
	}
	
	@RequestMapping("/check")
	public Map<String,String> checkOrder(@RequestBody ReceiveOrder param){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String checktime = df.format(new Date());// new Date()为获取当前系统时间
		param.setChecktime(checktime);
		param.setStatus("4");
		
		String infovalue = service.checkOrder(param);
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", infovalue);
		System.out.println("11111"+result);
		return result;//返回成功/失败信息
	}
	
	@RequestMapping("/pay")
	public Map<String,String> payOrder(@RequestBody ReceiveOrder param)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String gathertime = df.format(new Date());// new Date()为获取当前系统时间
		param.setGathertime(gathertime);
		param.setStatus("5");
		
		service.payOrder(param);
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", "收款成功");
		return result;//返回成功/失败信息
	}
	
	@RequestMapping("/return")
	public Map<String,String> returnOrder(@RequestBody ReceiveOrder param){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String returntime = df.format(new Date());// new Date()为获取当前系统时间
		param.setReturntime(returntime);
		param.setStatus("6");
		
		service.returnOrder(param);
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("info", "退货成功");
		return result;//返回成功/失败信息
	}
}