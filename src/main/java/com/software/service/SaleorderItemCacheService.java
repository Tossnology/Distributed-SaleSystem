package com.software.service;

import com.software.domain.Client;
import com.software.domain.SaleorderItem;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleorderItemCacheService implements SaleorderItemService {

    @Autowired
    private SaleorderItemService sis;

    @Autowired
    private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "saleorderitem";
    private String serviceName = "saleorderItemServiceImpl";
    private String getIdMethodName = "getViceid";

    @Override
    public void deleteByPrimaryKey(SaleorderItem record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderItem.class);
    }

    @Override
    public void deleteByID(SaleorderItem record) {
        sis.deleteByID(record);
    }

    @Override
    public void insert(SaleorderItem record) {
        sis.insert(record);
    }

    @Override
    public void insertSelective(SaleorderItem record) {
        sis.insertSelective(record);
    }

    @Override
    public SaleorderItem selectByPrimaryKey(SaleorderItem record) {
        if(record.getViceid()== null) {
            record.setViceid(sis.select(record).get(0).getViceid());
        }
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderItem.class);
    }

    @Override
    public List<SaleorderItem> select(SaleorderItem record) {
        return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderItem.class);
    }

    @Override
    public void updateByPrimaryKeySelective(SaleorderItem record) {
        if(record.getViceid()== null) {
            record.setViceid(sis.select(record).get(0).getViceid());
        }
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderItem.class);
    }

    @Override
    public void updateByPrimaryKey(SaleorderItem record) {
        if(record.getViceid()== null) {
            record.setViceid(sis.select(record).get(0).getViceid());
        }
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                SaleorderItem.class);
    }

    @Override
    public void createNewTable(SaleorderItem record) {
        sis.createNewTable(record);
    }

    @Override
    public void dropTable(SaleorderItem record) {
        sis.dropTable(record);
    }
}
