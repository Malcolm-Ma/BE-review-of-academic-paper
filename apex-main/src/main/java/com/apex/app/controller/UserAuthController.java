package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
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

    @GetMapping(value = "/login")
    public CommonResult<String> login() {
        return CommonResult.success("Hello world");
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
