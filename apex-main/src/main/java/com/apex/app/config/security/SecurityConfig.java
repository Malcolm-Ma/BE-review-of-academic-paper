package com.apex.app.config.security;

import com.apex.app.component.security.JwtAuthenticationTokenFilter;
import com.apex.app.component.security.RestAuthenticationEntryPoint;
import com.apex.app.component.security.RestfulAccessDeniedHandler;
import com.apex.app.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security config
 *
 * @author Mingze Ma
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private IgnoredUrlConfig ignoredUrlConfig;

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        //Resource paths that do not require protection are allowed access
        for (String url : ignoredUrlConfig.getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        //OPTIONS requests that allow cross-domain requests
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        // Any request requires authentication
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // Disable cross-site request protection and do not use session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Custom Permission Denial Handling Class
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                // Custom Permission Blocker JWT Filter
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
