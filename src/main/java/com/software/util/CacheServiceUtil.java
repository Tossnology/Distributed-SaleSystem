package com.software.util;

import com.software.util.RedisToDBUtil;
import com.software.util.RedisUtil;
import com.software.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 用于缓存与数据库结合，提供增查删改通用方法给service使用
 */
@Service
public class CacheServiceUtil {
    @Autowired
    private RedisUtil ru;

    @Autowired
    private RedisToDBUtil rtdb;

    private Map<String, Map<String, Set<String>>> keySetMap =
        new HashMap<String, Map<String, Set<String>>>();

    /**
     * 初始化KeySet
     * @param serviceName Service名称
     */
    private void initSet(String serviceName) {
        if(keySetMap.containsKey(serviceName)) return;
        Set<String> insertSet = new HashSet<>();
        Set<String> deleteSet = new HashSet<>();
        Set<String> updateSet = new HashSet<>();
        Map<String, Set<String>> key3Set = new HashMap<String, Set<String>>() {{
            put("insert", insertSet);
            put("delete", deleteSet);
            put("update", updateSet);
        }};
        keySetMap.put(serviceName, key3Set);
    }

    /**
     * 通过主键查找
     * @param serviceName
     * @param methodName
     * @param keyPrefix
     * @param paramObject
     * @param getIdMethodName
     * @param paramClazz
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     */
    public <T> T selectByPrimaryKey(String serviceName, String methodName,
                                    String keyPrefix, T paramObject, String getIdMethodName,
                                    Class<T> paramClazz) {
        if(!keySetMap.containsKey(serviceName)) {
            initSet(serviceName);
        }
        Object serviceInstance = SpringContextUtil.getBean(serviceName);
        Method getIdMethod = ReflectionUtils.findMethod(paramClazz, getIdMethodName);
        Method selectByKeyMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), methodName, paramClazz);
        Object id = ReflectionUtils.invokeMethod(getIdMethod, paramObject);
        String key = keyPrefix+ id;

        T resultObject = null; //结果变量
        if(ru.get(key)!=null && ru.get(key).equals("-1")) {
            resultObject = null;
        }
        else if((resultObject=(T)ru.getObject(key, paramObject.getClass()))==null) {
            Object[] param = new Object[1];
            param[0] = paramObject;
            resultObject = (T)ReflectionUtils.invokeMethod(selectByKeyMethod, serviceInstance, param);
            if(resultObject == null) {
                ru.set(key, "-1");
                ru.expire(key, ru.getExpirei());
            } else {
                ru.setObject(key, resultObject);
            }
        }
        if(keySetMap.get(serviceName).get("delete").contains(key)) {
            resultObject = null;
        }
        return resultObject;
    }

    /**
     * 条件查询方法
     * @param serviceName
     * @param methodName
     * @param keyPrefix
     * @param paramObject
     * @param getIdMethodName
     * @param paramClazz
     * @param <T>
     * @return
     */
    public <T> List<T> select(String serviceName, String methodName,
                              String keyPrefix, T paramObject, String getIdMethodName,
                              Class<T> paramClazz) {
        if(!keySetMap.containsKey(serviceName)) {
            initSet(serviceName);
        }
        Object serviceInstance = SpringContextUtil.getBean(serviceName);
        Method getIdMethod = ReflectionUtils.findMethod(paramClazz, getIdMethodName);
        Method selectMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), methodName, paramClazz);
        Object[] params = new Object[1];
        params[0] = paramObject;
        List<T> resultObjects = (List<T>)ReflectionUtils.invokeMethod(selectMethod, serviceInstance, params);
        for(int i = 0; i < resultObjects.size(); i++) {
            String key = keyPrefix+ ReflectionUtils.invokeMethod(getIdMethod, resultObjects.get(i));
            if(ru.existsKey(key)) {
                resultObjects.set(i, ru.getObject(key, paramClazz));
            } else {
                ru.setObject(key, resultObjects.get(i));
            }
        }
        for(T value: resultObjects) {
            String key = keyPrefix+ ReflectionUtils.invokeMethod(getIdMethod, value);
            if(keySetMap.get(serviceName).get("delete").contains(key)) {
                resultObjects.remove(value);
            }
        }
        return resultObjects;
    }

    /**
     * 通过主键删除
     * @param serviceName
     * @param methodName
     * @param keyPrefix
     * @param paramObject
     * @param getIdMethodName
     * @param paramClazz
     * @param <T>
     */
    public <T> void deleteByPrimaryKey(String serviceName, String methodName,
                                   String keyPrefix, T paramObject, String getIdMethodName,
                                   Class<T> paramClazz) {
        if(!keySetMap.containsKey(serviceName)) {
            initSet(serviceName);
        }
        Object serviceInstance = SpringContextUtil.getBean(serviceName);
        Method getIdMethod = ReflectionUtils.findMethod(paramClazz, getIdMethodName);
        Method deleteByIdMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), methodName, paramClazz);
        String key = keyPrefix+ReflectionUtils.invokeMethod(getIdMethod, paramObject);
        //同步删除
        Object[] params = new Object[1];
        params[0] = paramObject;
        ReflectionUtils.invokeMethod(deleteByIdMethod, serviceInstance, params);
        //同步删除，下面语句不再使用
//        keySetMap.get(serviceName).get("delete").add(key);
//        if(keySetMap.get(serviceName).get("delete").size() >= rtdb.getDeletemaxi()) {
//            rtdb.writeDelete(serviceName, methodName, paramClazz, keySetMap.get(serviceName).get("delete"));
//        }
        ru.removeValue(key);
    }

    /**
     * 通过主键更新
     * @param serviceName
     * @param updateMethodName
     * @param selectMethodName
     * @param keyPrefix
     * @param paramObject
     * @param getIdMethodName
     * @param paramClazz
     * @param <T>
     */
    public <T> void updateByPrimaryKey(String serviceName, String updateMethodName, String selectMethodName,
                                       String keyPrefix, T paramObject, String getIdMethodName,
                                       Class<T> paramClazz) {
        if(!keySetMap.containsKey(serviceName)) {
            initSet(serviceName);
        }
        Object serviceInstance = SpringContextUtil.getBean(serviceName);
        Method getIdMethod = ReflectionUtils.findMethod(paramClazz, getIdMethodName);
        Method selectByIdMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), selectMethodName, paramClazz);
        Method updateByIdMethod = ReflectionUtils.findMethod(serviceInstance.getClass(), updateMethodName, paramClazz);
        String key = keyPrefix + ReflectionUtils.invokeMethod(getIdMethod, paramObject);
        Object[] params = new Object[1];
        params[0] = paramObject;
        T resultObject = null;
        if((resultObject=ru.getObject(key, paramClazz))==null) {
            //若数据库中不存在，则设置空值
            if((resultObject=(T)ReflectionUtils.invokeMethod(selectByIdMethod, serviceInstance, params))== null) {
                ru.set(key, "-1");
                ru.expire(key, ru.getExpirei());
                return;
            }
            //否则先写回更新到数据库，然后更新缓存
            else {
                ReflectionUtils.invokeMethod(updateByIdMethod, serviceInstance, params);
                resultObject=(T)ReflectionUtils.invokeMethod(selectByIdMethod, serviceInstance, params);
                ru.setObject(key, resultObject);
            }
        } else {
            //若缓存中存在，则写入数据库后更新缓存
            ReflectionUtils.invokeMethod(updateByIdMethod, serviceInstance, params);
            resultObject=(T)ReflectionUtils.invokeMethod(selectByIdMethod, serviceInstance, params);
            ru.setObject(key, resultObject);
        }
        //因为已经同步写回数据库，所以下面语句不再使用
//        keySetMap.get(serviceName).get("update").add(key);
//        if(keySetMap.get(serviceName).get("update").size() >= rtdb.getUpdatemaxi()) {
//            rtdb.writeUpdate(serviceName, updateMethodName, paramClazz, keySetMap.get(serviceName).get("update"));
//        }
    }
}
