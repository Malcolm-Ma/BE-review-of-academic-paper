package com.apex.app.config.security;

import com.apex.app.component.security.JwtAuthenticationTokenFilter;
import com.apex.app.util.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * General Security Config of http request
 *
 * @author Mingze Ma
 */
@Configuration
public class GeneralSecurityConfig {

    @Bean
    IgnoredUrlConfig ignoredUrlConfig() {
        return new IgnoredUrlConfig();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

}
