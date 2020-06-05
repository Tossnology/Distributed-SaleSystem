package com.software.service;

import com.software.domain.Client;
import com.software.domain.Warehourse;
import com.software.domain.WarehourseDetail;
import com.software.util.CacheServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.CallbackHandler;
import java.util.List;

@Service
public class WarehourseDetailCacheService implements  WarehourseDetailService {
    @Autowired
    private WarehourseDetailService wds;

    @Autowired
    private CacheServiceUtil cacheServiceUtil;

    private String keyPrefix = "warehoursedetail";
    private String serviceName = "warehourseDetailServiceImpl";
    private String getIdMethodName = "getId";


    @Override
    public void deleteByPrimaryKey(WarehourseDetail record) {
        cacheServiceUtil.deleteByPrimaryKey(serviceName,
                "deleteByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseDetail.class);
    }

    @Override
    public void insert(WarehourseDetail record) {
        wds.insert(record);
    }

    @Override
    public void insertSelective(WarehourseDetail record) {
        wds.insertSelective(record);
    }

    @Override
    public WarehourseDetail selectByPrimaryKey(WarehourseDetail record) {
        if(record.getId()==null) {
            record.setId(wds.select(record).get(0).getId());
        }
        return cacheServiceUtil.selectByPrimaryKey(serviceName,
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseDetail.class);
    }

    @Override
    public List<WarehourseDetail> select(WarehourseDetail record) {
        return cacheServiceUtil.select(serviceName,
                "select",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseDetail.class);
    }

    @Override
    public void updateByPrimaryKeySelective(WarehourseDetail record) {
        if(record.getId()== null) {
            record.setId(wds.select(record).get(0).getId());
        }
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKeySelective",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseDetail.class);
    }

    @Override
    public void updateByPrimaryKey(WarehourseDetail record) {
        if(record.getId()== null) {
            record.setId(wds.select(record).get(0).getId());
        }
        cacheServiceUtil.updateByPrimaryKey(serviceName,
                "updateByPrimaryKey",
                "selectByPrimaryKey",
                keyPrefix,
                record,
                getIdMethodName,
                WarehourseDetail.class);
    }

    @Override
    public void createNewTable(WarehourseDetail record) {
        wds.createNewTable(record);
    }

    @Override
    public void dropTable(WarehourseDetail record) {
        wds.dropTable(record);
    }
}
