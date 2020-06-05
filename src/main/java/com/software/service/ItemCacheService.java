package com.software.service;

import com.software.domain.Item;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCacheService implements ItemService{

    @Autowired
    private ItemService is;

    @Autowired
    private CacheServiceUtil CacheServiceUtil;

    private String keyPrefix = "item";
    private String serviceName = "itemServiceImpl";
    private String getIdMethodName = "getId";

    @Override
    public void deleteByPrimaryKey(Item record) {
        CacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Item.class);
    }

    @Override
    public void insert(Item record) {
        is.insert(record);
    }

    @Override
    public void insertSelective(Item record) {
        is.insertSelective(record);
    }

    @Override
    public Item selectByPrimaryKey(Item record) {
        return CacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Item.class);
    }

    @Override
    public List<Item> select(Item record) {
        return CacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                Item.class);
    }

    @Override
    public void updateByPrimaryKeySelective(Item record) {
        CacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Item.class);
    }

    @Override
    public void updateByPrimaryKey(Item record) {
        CacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Item.class);
    }
}
