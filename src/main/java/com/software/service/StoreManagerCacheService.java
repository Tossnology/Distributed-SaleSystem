package com.software.service;

import com.software.domain.Client;
import com.software.domain.StoreManager;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreManagerCacheService implements StoreManagerService {
    @Autowired
    private StoreManagerService sms;

    @Autowired
    private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "storemanager";
    private String serviceName = "storeManagerServiceImpl";
    private String getIdMethodName = "getId";

    @Override
    public void deleteByPrimaryKey(StoreManager record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                StoreManager.class);
    }

    @Override
    public void insert(StoreManager record) {
        sms.insert(record);
    }

    @Override
    public void insertSelective(StoreManager record) {
        sms.insertSelective(record);
    }

    @Override
    public StoreManager selectByPrimaryKey(StoreManager record) {
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                StoreManager.class);
    }

    @Override
    public List<StoreManager> select(StoreManager record) {
        return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                StoreManager.class);
    }

    @Override
    public void updateByPrimaryKeySelective(StoreManager record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                StoreManager.class);
    }

    @Override
    public void updateByPrimaryKey(StoreManager record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                StoreManager.class);
    }

    @Override
    public int count() {
        return sms.count();
    }
}
