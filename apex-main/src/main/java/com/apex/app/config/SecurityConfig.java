package com.apex.app.config;

import com.apex.app.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Spring Security config
 *
 * @author Mingze Ma
 */
@Configuration
public class SecurityConfig {

    @Autowired
    UserAuthService userAuthService;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userAuthService.getUserByEmail(username);
    }

}
