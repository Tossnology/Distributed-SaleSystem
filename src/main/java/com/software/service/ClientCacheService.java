package com.software.service;

import com.software.domain.Client;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCacheService implements ClientService {

    @Autowired
    private ClientService cs;

    @Autowired
    private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "client";
    private String serviceName = "clientServiceImpl";
    private String getIdMethodName = "getId";

    @Override
    public void deleteByPrimaryKey(Client record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Client.class);
    }

    @Override
    public void insert(Client record) {
        cs.insert(record);
    }

    @Override
    public void insertSelective(Client record) {
        cs.insertSelective(record);
    }

    @Override
    public Client selectByPrimaryKey(Client record) {
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Client.class);
    }

    @Override
    public List<Client> select(Client record) {
         return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                Client.class);
    }

    @Override
    public void updateByPrimaryKeySelective(Client record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Client.class);
    }

    @Override
    public void updateByPrimaryKey(Client record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Client.class);
    }
}
