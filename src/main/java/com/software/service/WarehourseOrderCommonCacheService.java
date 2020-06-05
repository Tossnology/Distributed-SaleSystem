package com.software.service;

import com.software.domain.Client;
import com.software.domain.WarehourseOrderCommon;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehourseOrderCommonCacheService implements WarehourseOrderCommonService {
    @Autowired
    private WarehourseOrderCommonService wocs;

    @Autowired
    private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "warehourseordercommon";
    private String serviceName = "warehourseOrderCommonServiceImpl";
    private String getIdMethodName = "getId";

    @Override
    public void deleteByPrimaryKey(WarehourseOrderCommon record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderCommon.class);
    }

    @Override
    public void insert(WarehourseOrderCommon record) {
        wocs.insert(record);
    }

    @Override
    public void insertSelective(WarehourseOrderCommon record) {
        wocs.insertSelective(record);
    }

    @Override
    public WarehourseOrderCommon selectByPrimaryKey(WarehourseOrderCommon record) {
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderCommon.class);
    }

    @Override
    public List<WarehourseOrderCommon> select(WarehourseOrderCommon record) {
        return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderCommon.class);
    }

    @Override
    public void updateByPrimaryKeySelective(WarehourseOrderCommon record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderCommon.class);
    }

    @Override
    public void updateByPrimaryKey(WarehourseOrderCommon record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderCommon.class);
    }
}
