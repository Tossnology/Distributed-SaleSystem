package com.software.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * redis写回数据库工具类
 */
@Component
@ConfigurationProperties(prefix="redistodb")
public class RedisToDBUtil {
    @Autowired
    RedisUtil ru;

    private String insertmax;
    private String deletemax;
    private String updatemax;

    private int insertmaxi;
    private int deletemaxi;
    private int updatemaxi;

    public int getInsertmaxi() {
        this.insertmaxi = Integer.parseInt(insertmax);
        return insertmaxi;
    }

    public void setInsertmaxi(int insertmaxi) {
        this.insertmaxi = insertmaxi;
    }

    public int getDeletemaxi() {
        this.deletemaxi = Integer.parseInt(deletemax);
        return deletemaxi;
    }

    public void setDeletemaxi(int deletemaxi) {
        this.deletemaxi = deletemaxi;
    }

    public int getUpdatemaxi() {
        this.updatemaxi = Integer.parseInt(updatemax);
        return updatemaxi;
    }

    public void setUpdatemaxi(int updatemaxi) {
        this.updatemaxi = updatemaxi;
    }

    public String getInsertmax() {
        return insertmax;
    }

    public void setInsertmax(String insertmax) {
        this.insertmax = insertmax;
    }

    public String getDeletemax() {
        return deletemax;
    }

    public void setDeletemax(String deletemax) {
        this.deletemax = deletemax;
    }

    public String getUpdatemax() {
        return updatemax;
    }

    public void setUpdatemax(String updatemax) {
        this.updatemax = updatemax;
    }

    //插入删除缓存写入数据库
    public synchronized <T> void writeInsertAndDelete(String serviceName, String insertMethodName, String deleteMethodName,
                                                  Class<T> paramClazz, Set<String> insertKeySet, Set<String> deleteKeySet) {
        System.out.println("Start write insert to DB, insert max=" + insertmax);
        Set<String> ires = new HashSet<>(insertKeySet);
        ires.removeAll(deleteKeySet);
        Set<String> dres = new HashSet<>(deleteKeySet);
        dres.removeAll(insertKeySet);
        Object serviceInstance = SpringContextUtil.getBean(serviceName);
        Method insertMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), insertMethodName, paramClazz);
        Method deleteMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), deleteMethodName, paramClazz);
        //ires即 insert result， insert set中去除delete set剩下的元素
        try {
            for (String key : ires) {
                Object[] params = new Object[1];
                params[0] = ru.getObject(key, paramClazz);
                ReflectionUtils.invokeMethod(insertMethod, serviceInstance, params);
                ru.removeValue(key);
            }
            Thread.sleep(500);
            for (String key : ires) {
                ru.removeValue(key);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Write insert to DB success...");
        //dres = dset - iset
        System.out.println("Start write delete to DB, delete max=" + deletemax);
        try {
            for (String key : dres) {
                ru.removeValue(key);
                Object[] params = new Object[1];
                params[0] = ru.getObject(key, paramClazz);
                ReflectionUtils.invokeMethod(deleteMethod, serviceInstance, params);
            }
            Thread.sleep(500);
            for (String key : dres) {
                ru.removeValue(key);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Write delete to DB success...");
        insertKeySet.clear();
        deleteKeySet.clear();
    }

    //插入删除缓存写入数据库
    public synchronized <T> void writeDelete(String serviceName, String deleteMethodName,
                                                  Class<T> paramClazz, Set<String> deleteKeySet) {
        Object serviceInstance = SpringContextUtil.getBean(serviceName);
        Method deleteMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), deleteMethodName, paramClazz);
        System.out.println("Start write delete to DB, delete max=" + deletemax);
        try {
            for (String key : deleteKeySet) {
                ru.removeValue(key);
                Object[] params = new Object[1];
                params[0] = ru.getObject(key, paramClazz);
                ReflectionUtils.invokeMethod(deleteMethod, serviceInstance, params);
            }
            Thread.sleep(500);
            for (String key : deleteKeySet) {
                ru.removeValue(key);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Write delete to DB success...");
        deleteKeySet.clear();
    }

    //更新缓存写入数据库
    public synchronized <T> void writeUpdate(String serviceName, String updateMethodName,
                                         Class<T> paramClazz, Set<String> updateKeySet) {
        System.out.println("Start write update to DB, updatemax=" + updatemax);
        Object serviceInstance = SpringContextUtil.getBean(serviceName);
        Method updateMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), updateMethodName, paramClazz);
        try {
            for (String key : updateKeySet) {
                Object[] params = new Object[1];
                params[0] = ru.getObject(key, paramClazz);
                ReflectionUtils.invokeMethod(updateMethod, serviceInstance, params);
                ru.removeValue(key);
            }
            Thread.sleep(500);
            for (String key : updateKeySet) {
                ru.removeValue(key);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Write update to DB success...");
        updateKeySet.clear();
    }

    @Override
    public String toString() {
        return "RedisToDBUtil{" +
                "rtu=" + ru +
                ", insertmax='" + insertmax + '\'' +
                ", deletemax='" + deletemax + '\'' +
                ", updatemax='" + updatemax + '\'' +
                ", insertmaxi=" + insertmaxi +
                ", deletemaxi=" + deletemaxi +
                ", updatemaxi=" + updatemaxi +
                '}';
    }
}
