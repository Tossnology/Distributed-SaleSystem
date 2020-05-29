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

import com.software.domain.WarehourseOrderCommon;
import com.software.topservice.WarehourseOrderManagerService;
import com.software.trans.ReceiveWarehourseOrder;
import com.software.trans.SendWarehourseOrder;
import com.software.trans.Stock;

@RestController
@RequestMapping("/warehourseOrder")
public class WarehourseOrderController 
{
	@Autowired
	private WarehourseOrderManagerService service;
	
	@RequestMapping("/queryById")
	public SendWarehourseOrder queryWarehourseOrderById(@RequestBody ReceiveWarehourseOrder param){
		SendWarehourseOrder result = service.select(param).get(0);
		return result;
	}
	
	@RequestMapping("/query")
	public List<SendWarehourseOrder> queryWarehourseOrder(@RequestBody ReceiveWarehourseOrder param){
		List<SendWarehourseOrder> result = service.select(param);
		return result;
	}
	
	@RequestMapping("/insert")
	public Map<String, String> insertWarehourseOrder(@RequestBody List<ReceiveWarehourseOrder> param){
		Map<String, String> result = new HashMap<String, String>();
		if(param.get(0).getSourceid().equals(param.get(0).getTargetid())){
			result.put("info", "不能我发我自己");
		}else{
			if (param.size() == 0) {
				return null;
			}
			param.get(0).setStatus(1 + "");
			System.out.println("订单号：" + param.get(0).getId());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String time = df.format(new Date());// new Date()为获取当前系统时间
			for (ReceiveWarehourseOrder s : param) {
				s.setCreatetime(time);
			}
			result.put("info", "添加成功");
			service.insert(param);
		}
		return result;
	}
	
	@RequestMapping("/update")
	public Map<String, String> updateWarehourseOrder(@RequestBody List<ReceiveWarehourseOrder> param){
		Map<String, String> result = new HashMap<String, String>();
		
		if (param.size()==0) 
		{
			result.put("info", "没有订单");
		}else{
			result.put("info", "修改成功");
		}
		param.get(0).setStatus(1+"");
		service.update(param);
		return result;
	}
	
	@RequestMapping("/delete")
	public String deleteWarehourseOrder(@RequestBody ReceiveWarehourseOrder param){
		service.delete(param);
		return "success";
	}
	
	@RequestMapping("/apply")
	public Map<String, String> applyWarehourseOrder(@RequestBody ReceiveWarehourseOrder param){
		// update
		param.setStatus(2+"");
		
		service.updateStatus(param);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "已提交，请等待审核");
		return result;
	}
	
	@RequestMapping("/pass")
	public Map<String, String> passWarehourseOrder(@RequestBody ReceiveWarehourseOrder param)
	{
		// check
		param.setStatus(4+"");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		param.setChecktime(time);
		
		System.out.println(param);
		
		String infovalue = service.checkOrder(param);
		Map<String, String> result = new HashMap<String, String>();
		result.put("info", infovalue);
		return result;
	}
	
	@RequestMapping("/inoutmoney")
	public Map<String, String> inoutMoney(@RequestBody Map<String, String> param)
	{
		
		Integer warehourseid = Integer.valueOf(param.get("warehourseid"));
		
		return service.inoutMoney(warehourseid);
		
	}
}
