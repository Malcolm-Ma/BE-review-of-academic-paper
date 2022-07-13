package com.apex.app.service;

import com.apex.app.model.UserBase;

/**
 * Cache service interface
 *
 * @author Mingze Ma
 */
public interface CacheService {

    void setUser(UserBase user);

    UserBase getUser(String username);

    void deleteUser(String uid);

}
