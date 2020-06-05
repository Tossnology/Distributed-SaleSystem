package com.software.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.software.domain.Client;
import com.software.domain.SaleorderCommon;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleorderCommonCacheService implements SaleorderCommonService {

    @Autowired
    private SaleorderCommonService scs;

    @Autowired
    private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "saleordercommon";
    private String serviceName = "saleorderCommonServiceImpl";
    private String getIdMethodName = "getId";

    @Override
    public void deleteByPrimaryKey(SaleorderCommon record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderCommon.class);
    }

    @Override
    public void insert(SaleorderCommon record) {
        scs.insert(record);
    }

    @Override
    public void insertSelective(SaleorderCommon record) {
        scs.insertSelective(record);
    }

    @Override
    public SaleorderCommon selectByPrimaryKey(SaleorderCommon record) {
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderCommon.class);
    }

    @Override
    public List<SaleorderCommon> select(SaleorderCommon record) {
        return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderCommon.class);
    }

    @Override
    public void updateByPrimaryKeySelective(SaleorderCommon record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderCommon.class);
    }

    @Override
    public void updateByPrimaryKey(SaleorderCommon record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderCommon.class);
    }

    @Override
    public void createNewTable(SaleorderCommon record) {
        scs.createNewTable(record);
    }

    @Override
    public void dropTable(SaleorderCommon record) {
        scs.dropTable(record);
    }
}
