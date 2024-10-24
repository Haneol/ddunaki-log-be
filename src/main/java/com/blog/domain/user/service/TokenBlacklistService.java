package com.blog.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenBlacklistService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String BLACKLIST_PREFIX = "blacklist_";

    public void addToBlackList(String token, long expirationTime) {
        redisTemplate.opsForValue().set(BLACKLIST_PREFIX+token,"true", expirationTime, TimeUnit.MILLISECONDS);
    }

    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(BLACKLIST_PREFIX + token);
    }
}
