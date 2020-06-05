package com.software.service;

import com.software.domain.Client;
import com.software.domain.WarehourseOrderItem;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehourseOrderItemCacheService implements WarehourseOrderItemService {
   @Autowired
   private WarehourseOrderItemService wois;

   @Autowired
   private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "warehourseorderitem";
    private String serviceName = "warehourseOrderItemServiceImpl";
    private String getIdMethodName = "getViceid";

    @Override
    public void deleteByPrimaryKey(WarehourseOrderItem record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderItem.class);
    }

    @Override
    public void deleteByID(WarehourseOrderItem record) {
        wois.deleteByID(record);
    }

    @Override
    public void insert(WarehourseOrderItem record) {
        wois.insert(record);
    }

    @Override
    public void insertSelective(WarehourseOrderItem record) {
        wois.insertSelective(record);
    }

    @Override
    public WarehourseOrderItem selectByPrimaryKey(WarehourseOrderItem record) {
        if(record.getViceid()== null) {
            record.setViceid(wois.select(record).get(0).getViceid());
        }
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderItem.class);
    }

    @Override
    public List<WarehourseOrderItem> select(WarehourseOrderItem record) {
        return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderItem.class);
    }

    @Override
    public void updateByPrimaryKeySelective(WarehourseOrderItem record) {
        if(record.getViceid()== null) {
            record.setViceid(wois.select(record).get(0).getViceid());
        }
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderItem.class);
    }

    @Override
    public void updateByPrimaryKey(WarehourseOrderItem record) {
        if(record.getViceid()== null) {
            record.setViceid(wois.select(record).get(0).getViceid());
        }
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseOrderItem.class);
    }
}
