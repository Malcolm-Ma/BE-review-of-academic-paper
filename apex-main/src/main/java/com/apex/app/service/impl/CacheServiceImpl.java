package com.apex.app.service.impl;

import com.apex.app.mapper.UserBaseMapper;
import com.apex.app.domain.model.UserBase;
import com.apex.app.service.CacheService;
import com.apex.app.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Mingze Ma
 */
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserBaseMapper userBaseMapper;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;

    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;

    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;

    @Override
    public void setUser(UserBase user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getId();
        redisService.set(key, user, REDIS_EXPIRE);
    }

    @Override
    public UserBase getUser(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + username;
        return (UserBase) redisService.get(key);
    }

    @Override
    public void deleteUser(String uid) {
        UserBase user = userBaseMapper.selectByPrimaryKey(uid);
        if (user != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getId();
            redisService.del(key);
        }
    }
}
