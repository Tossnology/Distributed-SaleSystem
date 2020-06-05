package com.software.service;

import com.software.domain.GeneralManager;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralManagerCacheService implements GeneralManagerService {

    @Autowired
    private GeneralManagerService gms;

    @Autowired
    private CacheServiceUtil CacheServiceUtil;

    private String keyPrefix = "generalmanager";
    private String serviceName = "generalManagerServiceImpl";
    private String getIdMethodName = "getId";

    @Override
    public void deleteByPrimaryKey(GeneralManager record) {
        CacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                GeneralManager.class);
    }

    @Override
    public void insert(GeneralManager record) {
        gms.insert(record);
    }

    @Override
    public void insertSelective(GeneralManager record) {
        gms.insertSelective(record);
    }

    @Override
    public GeneralManager selectByPrimaryKey(GeneralManager record) {
        return CacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                GeneralManager.class);
    }

    @Override
    public void updateByPrimaryKeySelective(GeneralManager record) {
        CacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                GeneralManager.class);
    }

    @Override
    public void updateByPrimaryKey(GeneralManager record) {
        CacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                GeneralManager.class);
    }
}
