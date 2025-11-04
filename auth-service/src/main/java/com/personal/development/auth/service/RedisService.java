package com.personal.development.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setValue(String jwt, boolean isBlacklisted){
        redisTemplate.opsForValue().set(jwt, String.valueOf(isBlacklisted));
    }

    public boolean getValue(String key){
        String value = redisTemplate.opsForValue().get(key);
        return Boolean.parseBoolean(value);
    }

    public void setValueWithExpiry(String jwt, boolean isBlacklisted, long seconds) {
        redisTemplate.opsForValue().set(jwt, String.valueOf(isBlacklisted), seconds, TimeUnit.MILLISECONDS);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
