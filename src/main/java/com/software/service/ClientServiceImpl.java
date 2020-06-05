package com.software.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.software.util.RedisToDBUtil;
import com.software.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.software.dao.ClientMapper;
import com.software.domain.Client;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientMapper cm;

//	@Autowired
//	private RedisUtil ru;
//
//	@Autowired
//	private RedisToDBUtil rtdb;
//
//	@Autowired
//	private aService a;

	@Override
	public void deleteByPrimaryKey(Client record) 
	{
//		String key = "client"+record.getId();
//		deleteSet.add(key);
//		if(deleteSet.size() >= rtdb.getDeletemaxi()) {
////			rtdb.writeInsertAndDelete(ClientServiceImp, );
//		}
//		ru.removeValue(key);
		cm.deleteByPrimaryKey(record);
	}

	@Override
	public void insert(Client record) 
	{
		cm.insert(record);
	}

	@Override
	public void insertSelective(Client record) 
	{
		cm.insertSelective(record);
	}

	@Override
	public List<Client> select(Client record) 
	{
//		List<Client> tmplist = cm.select(record);
//		//如果缓存中存在相关对象，将数据库中替换为缓存中对象
//		for(int i=0; i < tmplist.size(); i++) {
//			String key = "client"+tmplist.get(i).getId();
//			//缓存中存在，则替换
//			if(ru.existsKey(key)) {
//				tmplist.set(i, ru.getObject(key, Client.class));
//			}
//			//缓存不存在，则缓存
//			else {
//				ru.setObject(key, tmplist.get(i));
//			}
//		}
//		for(Client value: tmplist) {
//			String key = "client"+value.getId();
//			if(deleteSet.contains(key)) {
//				tmplist.remove(value);
//			}
//		}
//		return tmplist;
		return cm.select(record);
	}
	
	@Override
	public Client selectByPrimaryKey(Client record) 
	{
//		String key = "client"+record.getId();
//		Client client = null;
//		if(ru.get(key)!=null && ru.get(key).equals("-1")) {
//			client = null;
//		}
//		else if((client = ru.getObject(key, Client.class)) == null) {
//			client = cm.selectByPrimaryKey(record);
//			if(client == null) {
//				ru.set(key, "-1");
//				ru.expire(key, ru.getExpirei());
//			} else {
//				ru.setObject(key, client);
//			}
//		}
//		if(deleteSet.contains(key)) {
//			client = null;
//		}
		return cm.selectByPrimaryKey(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Client record) 
	{
		cm.updateByPrimaryKeySelective(record);

	}

	@Override
	public void updateByPrimaryKey(Client record) {
//		String key = "client" + record.getId();
//		Client client = null;
//		//由于insert数据库之后才能获取到id，对缓存穿透不做处理
//		if (ru.get(key) != null && ru.get(key).equals("-1")) {
////			client = null;
//		}
//			if ((client = ru.getObject(key, Client.class)) == null) {
//				if ((client = cm.selectByPrimaryKey(record)) == null) {
//					ru.set(key, "-1");
//					ru.expire(key, ru.getExpirei());
//					return;
//				} else {
//					ru.setObject(key, record);
//				}
//			} else {
//				ru.setObject(key, record);
//			}
//			updateSet.add(key);
//			if (updateSet.size() >= rtdb.getUpdatemaxi()) {
////					rtdb.writeUpdate();
//			}
		cm.updateByPrimaryKey(record);
		}
}
