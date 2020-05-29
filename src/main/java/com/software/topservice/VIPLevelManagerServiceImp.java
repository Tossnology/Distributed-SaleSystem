package com.software.topservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.domain.Client;
import com.software.domain.VIPLevel;
import com.software.service.ClientService;
import com.software.service.VIPLevelService;

@Service
public class VIPLevelManagerServiceImp implements VIPLevelManagerService 
{
	@Autowired
	private VIPLevelService service;
	
	@Autowired
	private ClientService clientService;
	
	@Override
	public List<VIPLevel> select(VIPLevel record) 
	{
		return service.select(record);
	}

	@Override
	public void updateByPrimaryKey(VIPLevel record) 
	{
		// 更新VIP信息，还需要更新客人信息
		service.updateByPrimaryKeySelective(record);
		
		Client exampleClient = new Client();
		exampleClient.setAuthority(record.getId()+"");
		List<Client> clientList = clientService.select(exampleClient);
		for (Client client : clientList) 
		{
			client.setPointtoprice(record.getPointtoprice());
			client.setPricetopoint(record.getPricetopoint());
			clientService.updateByPrimaryKeySelective(client);
		}
	}

	@Override
	public Map<Integer, VIPLevel> vipMenu()
	{
		List<VIPLevel> list = service.select(null);
		Map<Integer, VIPLevel> idToVIP = new HashMap<Integer, VIPLevel>();
		for (VIPLevel vipLevel : list) 
		{
			idToVIP.put(vipLevel.getId(), vipLevel);
		}
		return idToVIP;
	}

	@Override
	public void updateclient(String cID, String vID, String point) 
	{
		Integer vipID = Integer.valueOf(vID);
		Integer clientID = Integer.valueOf(cID);
		Float pointNum = null;
		if (!point.equals("")) 
		{
			pointNum = Float.valueOf(point);
		}
		
		VIPLevel exampleVIP = new VIPLevel();
		exampleVIP.setId(vipID);
		VIPLevel resultVIP = service.selectByPrimaryKey(exampleVIP);
		
		Client client = new Client();
		client.setId(clientID);
		client.setAuthority(vID);
		client.setPointtoprice(resultVIP.getPointtoprice());
		client.setPricetopoint(resultVIP.getPricetopoint());
		client.setPoint(pointNum);
		clientService.updateByPrimaryKeySelective(client);
	}
}
