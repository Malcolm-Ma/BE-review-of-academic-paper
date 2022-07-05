package com.apex.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {

    @PostMapping(value = "/login")
    public String login() {
        return "Hello World";
    }

    @PostMapping(value = "/register")
    public String register() {
        return "Hello World";
    }
}
