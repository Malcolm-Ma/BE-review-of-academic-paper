package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAuthController {

    @GetMapping(value = "/login")
    public CommonResult<String> login() {
        return CommonResult.success("Hello world");
    }

    @PostMapping(value = "/register")
    public String register() {
        return "Hello World";
    }
}
