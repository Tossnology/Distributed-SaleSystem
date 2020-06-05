package com.software.service;

import com.software.domain.Client;
import com.software.domain.ItemToPrice;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemToPriceCacheService implements ItemToPriceService {

    @Autowired
    private ItemToPriceService itps;

    @Autowired
    private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "itemtoprice";
    private String serviceName = "itemToPriceServiceImpl";
    private String getIdMethodName = "getId";

    @Override
    public void deleteByPrimaryKey(ItemToPrice record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                ItemToPrice.class);
    }

    @Override
    public void insert(ItemToPrice record) {
        itps.insert(record);
    }

    @Override
    public void insertSelective(ItemToPrice record) {
        itps.insertSelective(record);
    }

    @Override
    public ItemToPrice selectByPrimaryKey(ItemToPrice record) {
        if(record.getId()== null) {
            record.setId(itps.select(record).get(0).getId());
        }
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                ItemToPrice.class);
    }

    @Override
    public List<ItemToPrice> select(ItemToPrice record) {
        return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                ItemToPrice.class);
    }

    @Override
    public void updateByPrimaryKeySelective(ItemToPrice record) {
        if(record.getId()== null) {
            record.setId(itps.select(record).get(0).getId());
        }
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                ItemToPrice.class);
    }

    @Override
    public void updateByPrimaryKey(ItemToPrice record) {
        if(record.getId()== null) {
            record.setId(itps.select(record).get(0).getId());
        }
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                ItemToPrice.class);
    }

    @Override
    public void createNewTable(ItemToPrice record) {
        itps.createNewTable(record);
    }

    @Override
    public void dropTable(ItemToPrice record) {
        itps.dropTable(record);
    }
}
