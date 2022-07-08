package com.apex.app.service;

import com.apex.app.controller.vo.UserLoginRequest;
import com.apex.app.controller.vo.UserRegisterRequest;
import com.apex.app.model.UserBase;

/**
 * User authentication interface
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
    UserBase login(UserLoginRequest userLoginRequest);

}
