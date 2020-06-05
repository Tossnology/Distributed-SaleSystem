package com.software.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Redis工具类用于实现redis对java对象的存取操作
 */
@Component
@ConfigurationProperties(prefix="redisutil")
public class RedisUtil {

    @Autowired
    JedisPool jedisPool;

    private String expire;
    private int expirei;

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public int getExpirei() {
        this.expirei = Integer.parseInt(expire);
        return expirei;
    }

    public void setExpirei(int expirei) {
        this.expirei = expirei;
    }

    public boolean existsKey(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.exists(key) || jedis.exists(key.getBytes());
        } finally {
            jedis.close();
        }
    }

    public <T> List<T> getObjects(String key, Class<T> clazz) {
        Jedis jedis = jedisPool.getResource();
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        byte[] bytes = jedis.get(key.getBytes());
        if(bytes==null) return null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            return (List<T>)ois.readObject();
        } catch(Exception e) {
            System.out.println("Error in jedis read objects");
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                ois.close();
            } catch(Exception e) {
                System.out.println("Error in jedis close stream");
                e.printStackTrace();
            }
            jedis.close();
        }
        return null;
    }

    public <T> T getObject(String key, Class<T> clazz) {
        Jedis jedis = jedisPool.getResource();
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        byte[] bytes = jedis.get(key.getBytes());
        if(bytes == null) return null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            return (T)ois.readObject();
        } catch(Exception e) {
            System.out.println("Error in jedis read object");
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                ois.close();
            } catch(Exception e) {
                System.out.println("Error in jedis close inputstream");
                e.printStackTrace();
            }
            jedis.close();
        }
        return null;
    }

    public void setObject(String key, Object object) {
        Jedis jedis = jedisPool.getResource();
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            byte[] bytes = bos.toByteArray();
            // 存入二进制数据到Redis缓存中
            jedis.set(key.getBytes(), bytes);
        } catch (Exception e) {
            System.out.println("Error in jedis write object");
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                oos.close();
            } catch (Exception e2) {
                System.out.println("Error in jedis close outputstream");
                e2.printStackTrace();
            }
            jedis.close();
        }
    }

    public void removeValue(String key) {
        Jedis jedis = jedisPool.getResource();
        if(jedis.exists(key)) {
            jedis.del(key);
        } else if(jedis.exists(key.getBytes())) {
            jedis.del(key.getBytes());
        }
        jedis.close();
    }

    public void expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        if(jedis.exists(key)) {
            jedis.expire(key, seconds);
        }else if(jedis.exists(key.getBytes())) {
            jedis.expire(key.getBytes(), seconds);
        }
        jedis.close();
    }

    public void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedis.close();
    }

    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }
}
