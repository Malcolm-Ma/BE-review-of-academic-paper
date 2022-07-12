package com.apex.app.config.security;

import com.apex.app.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 
 * @author Mingze Ma
 */
@Configuration
public class ApexSecurityConfig {

    @Autowired
    UserAuthService userAuthService;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userAuthService.getUserByEmail(username);
    }

}
