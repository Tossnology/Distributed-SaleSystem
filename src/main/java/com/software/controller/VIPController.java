package com.software.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.domain.Client;
import com.software.domain.VIPLevel;
import com.software.topservice.ClientManagerService;
import com.software.topservice.VIPLevelManagerService;
import com.software.trans.ReceiveClient;

@RestController
@RequestMapping("/vip")
public class VIPController {

	@Autowired
	private VIPLevelManagerService vipService;
	
	@Autowired
	private ClientManagerService clientService;
	
	// 设定积分规则
	@RequestMapping("/updatevip")
	public Map<String, String> updatevip(@RequestBody Map<String, String> param)
	{
		Map<String, String> result = new HashMap<String, String>();

		try
		{
			Integer vipid = Integer.valueOf(param.get("id"));
			String vipname = param.get("vipname");
			Float pointtoprice = Float.valueOf(param.get("pointtoprice"));
			Float pricetopoint = Float.valueOf(param.get("pricetopoint"));
			if (pointtoprice<0) 
			{
				result.put("info", "积分兑换系数不正确");
				return result;
			}
			if (pricetopoint<0) 
			{
				result.put("info", "金额兑换系数不正确");
				return result;
			}
			VIPLevel vipLevel = new VIPLevel();
			vipLevel.setId(vipid);
			vipLevel.setVipname(vipname);
			vipLevel.setPointtoprice(pointtoprice);
			vipLevel.setPricetopoint(pricetopoint);
			System.out.println(vipLevel);
			vipService.updateByPrimaryKey(vipLevel);
			result.put("info", "更新成功");
			return result;
		}
		catch (Exception e) 
		{
			result.put("info", "请输入正确的数字");
			return result;
		}
	}
	
	// 取消会员
	@RequestMapping("/cancel")
	public Map<String, String> cancelAuthority(@RequestBody Map<String, String> param)
	{
		Client client = new Client();
		client.setId(Integer.valueOf(param.get("id")));
		client.setAuthority("-1");
		clientService.updateByPrimaryKeySelective(client);
		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "取消成功");
		return result;
	}

	// 将一个用户注册成会员， 编辑用户会员信息
	@RequestMapping("/updateclientinvip")
	public Map<String, String> updateclient(@RequestBody Map<String, String> param)
	{
		String clientid = param.get("id");
		String vipid = param.get("authority");
		String point = param.get("point");
		vipService.updateclient(clientid, vipid, point);
		Map<String, String> result = new HashMap<String, String>();
		result.put("info", "操作成功");
		return result;
	}
	
	@RequestMapping("/menu")
	public Map<Integer, VIPLevel> vipMenu()
	{
		return vipService.vipMenu();
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
		System.out.println(client);
		List<Client> result = clientService.select(client);
		List<Client> resultAfterFilter = new ArrayList<Client>();
		for (Client client2 : result) 
		{
			if (!client2.getAuthority().equals("-1")) 
			{
				resultAfterFilter.add(client2);
			}
		}
		return resultAfterFilter;
	}
}
