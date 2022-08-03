package com.apex.app.service;

import com.apex.app.controller.vo.UserInfoResponse;
import com.apex.app.controller.vo.UserLoginRequest;
import com.apex.app.controller.vo.UserRegisterRequest;
import com.apex.app.domain.bo.UserSearchResultBo;
import com.apex.app.domain.model.UserBase;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * User authentication interface
 *
 * @author Mingze Ma
 */
public interface UserAuthService {

    /**
     * Register function
     */
    UserBase register(UserRegisterRequest userRegisterRequest);

    /**
     * Login function
     */
    String login(UserLoginRequest userLoginRequest);

    /**
     * JWT auth function
     *
     * @param username username
     * @return UserDetails class
     */
    UserDetails getUserByUsername(String username);

    UserBase getUserById(String id);

    CacheService getCacheService();

    UserBase getCurrentUser();

    void logout();

    List<UserSearchResultBo> searchUser(String searchContent, Integer limit);

}
