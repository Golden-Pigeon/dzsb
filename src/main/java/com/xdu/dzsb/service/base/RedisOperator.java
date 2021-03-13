package com.xdu.dzsb.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @Description: redis的相关操作类
 */
@Service
public class RedisOperator {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public void set(String key, String value, long timeout) {
        
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }
    
    public Boolean hasKey(String key) {
        
        return redisTemplate.hasKey(key);
    }
    
    public String getValue(String key) {
        
        return redisTemplate.opsForValue().get(key);
    }
    
    public void deleteByKey(String key) {
        redisTemplate.delete(key);
    }
}