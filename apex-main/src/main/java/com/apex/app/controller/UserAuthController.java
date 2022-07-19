package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.UserInfoResponse;
import com.apex.app.controller.vo.UserLoginRequest;
import com.apex.app.controller.vo.UserRegisterRequest;
import com.apex.app.domain.model.UserBase;
import com.apex.app.service.UserAuthService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "User Authentication Controller")
@RestController
@RequestMapping("/user")
public class UserAuthController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(value = "/login")
    public CommonResult<Map<String, String>> login(@Validated @RequestBody UserLoginRequest userLoginRequest) {
        String token = userAuthService.login(userLoginRequest);
        if (token == null) {
            return CommonResult.validateFailed("Incorrect username or password");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
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

    @ApiOperation(value = "User Current Information")
    @GetMapping("/info/get")
    @ResponseBody
    public CommonResult<UserInfoResponse> getUserInfo() {
        UserBase user = userAuthService.getCurrentUser();
        UserInfoResponse resp = new UserInfoResponse(user);
        return CommonResult.success(resp);
    }
}
