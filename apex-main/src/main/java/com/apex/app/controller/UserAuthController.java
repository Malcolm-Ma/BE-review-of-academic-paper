package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.UserLoginRequest;
import com.apex.app.controller.vo.UserRegisterRequest;
import com.apex.app.model.UserBase;
import com.apex.app.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User Authentication Controller")
@RestController
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(value = "/login")
    public CommonResult<UserBase> login(@Validated @RequestBody UserLoginRequest userLoginRequest) {
        UserBase userBase = userAuthService.login(userLoginRequest);
        return userBase == null
                ? CommonResult.validateFailed("Incorrect username or password")
                : CommonResult.success(userBase);
    }

    @ApiOperation(value = "User Registration")
    @PostMapping(value = "/register")
    @ResponseBody
    public CommonResult<UserBase> register(@Validated @RequestBody UserRegisterRequest userRegisterRequest) {
        UserBase userBase = userAuthService.register(userRegisterRequest);
        if (userBase == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(userBase);
    }
}
