package com.software.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.topservice.ClientManagerService;
import com.software.trans.ReceiveClient;


@RestController
@RequestMapping("/client")
public class ClientController 
{
	
	@Autowired
	private ClientManagerService service;
	
	@RequestMapping("/queryById")
	public com.software.domain.Client queryClientById(@RequestBody Map<String, String> param){
		String id = param.get("id");
		com.software.domain.Client result = new com.software.domain.Client();
		result.setId(Integer.valueOf(id));
		result.setLabel("valid");
		return service.selectByPrimaryKey(result);
	}
	
	@RequestMapping("/query")
	public List<com.software.domain.Client> queryClient(@RequestBody ReceiveClient param){
		com.software.domain.Client client = new com.software.domain.Client();
		if (!param.getId().equals("")) 
		{
			client.setId(Integer.valueOf(param.getId()));
		}
		client.setName(param.getName());
		client.setGender(param.getGender());
		client.setPhone(param.getPhone());
		client.setNote(param.getNote());
		client.setEmail(param.getEmail());
		client.setType(param.getType());
		client.setLabel("valid");
		List<com.software.domain.Client> result = service.select(client);
		return result;
	}
	
	@RequestMapping("/insert")
	public Map<String, String> addClient(@RequestBody ReceiveClient param){
		com.software.domain.Client client = new com.software.domain.Client();
		if (!param.getId().equals("")) 
		{
			client.setId(Integer.valueOf(param.getId()));
		}
		client.setName(param.getName());
		client.setGender(param.getGender());
		client.setPhone(param.getPhone());
		client.setNote(param.getNote());
		client.setEmail(param.getEmail());
		client.setType(param.getType());
		client.setLabel("valid");
		service.insertSelective(client);

		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "添加成功");
		return result;
	}
	
	@RequestMapping("/delete")
	public Map<String, String> deleteClient(@RequestBody Map<String, String> param)
	{
		com.software.domain.Client client = new com.software.domain.Client();
		String id = param.get("id");
		client.setId(Integer.valueOf(id));
		client.setLabel("invalid");
		service.updateByPrimaryKeySelective(client);

		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "删除成功");
		return result;
	}
	
	@RequestMapping("/update")
	public Map<String, String> updateClient(@RequestBody ReceiveClient param){
		com.software.domain.Client client = new com.software.domain.Client();
		client.setId(Integer.valueOf(param.getId()));
		client.setName(param.getName());
		client.setGender(param.getGender());
		client.setPhone(param.getPhone());
		client.setNote(param.getNote());
		client.setEmail(param.getEmail());
		client.setType(param.getType());
		client.setLabel("valid");
		client.setAuthority(param.getAuthority());
		if (!param.getRemain().equals("")) {
			client.setRemain(Float.valueOf(param.getRemain()));
		}
		if (!param.getDebt().equals("")) {
			client.setDebt(Float.valueOf(param.getDebt()));
		}
		if (!param.getPoint().equals("")) {
			client.setPoint(Float.valueOf(param.getPoint()));
		}
		if (!param.getPricetopoint().equals("")) {
			client.setPricetopoint(Float.valueOf(param.getPricetopoint()));
		}
		if (!param.getPointtoprice().equals("")) {
			client.setPointtoprice(Float.valueOf(param.getPointtoprice()));
		}
		service.updateByPrimaryKeySelective(client);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "充值成功");
		return result;
	}
}
