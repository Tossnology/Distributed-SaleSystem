package com.software.service;

import com.software.domain.Client;
import com.software.domain.Staff;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffCacheService implements StaffService {
    @Autowired
    private StaffService ss;

    @Autowired
    private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "staff";
    private String serviceName = "staffServiceImpl";
    private String getIdMethodName = "getId";


    @Override
    public void deleteByPrimaryKey(Staff record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Staff.class);
    }

    @Override
    public void insert(Staff record) {
        ss.insert(record);
    }

    @Override
    public void insertSelective(Staff record) {
        ss.insertSelective(record);
    }

    @Override
    public Staff selectByPrimaryKey(Staff record) {
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Staff.class);
    }

    @Override
    public List<Staff> select(Staff record) {
        return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                Staff.class);
    }

    @Override
    public void updateByPrimaryKeySelective(Staff record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Staff.class);
    }

    @Override
    public void updateByPrimaryKey(Staff record) {
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                Staff.class);
    }

    @Override
    public void createNewTable(Staff record) {
        ss.createNewTable(record);
    }

    @Override
    public void dropTable(Staff record) {
        ss.dropTable(record);
    }

    @Override
    public int count(Staff record) {
        return ss.count(record);
    }
}
