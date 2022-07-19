package com.apex.app.service.impl;

import com.apex.app.common.exception.Asserts;
import com.apex.app.controller.vo.UserLoginRequest;
import com.apex.app.controller.vo.UserRegisterRequest;
import com.apex.app.domain.bo.CustomUserDetails;
import com.apex.app.mapper.UserBaseMapper;
import com.apex.app.domain.model.UserBase;
import com.apex.app.domain.model.UserBaseExample;
import com.apex.app.service.CacheService;
import com.apex.app.service.UserAuthService;
import com.apex.app.util.JwtTokenUtil;
import com.apex.app.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * User authentication implementation
 * @author Mingze Ma
 */
@Service
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserBaseMapper userBaseMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private UserBase getUserFromCache(String username) {
        UserBase user = getCacheService().getUser(username);
        if (user != null) {
            return user;
        }
        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andEmailEqualTo(username);
        List<UserBase> result = userBaseMapper.selectByExample(example);
        if (result != null && result.size() > 0) {
            user = result.get(0);
            getCacheService().setUser(user);
            return user;
        }
        return null;
    }

    @Override
    public UserBase register(UserRegisterRequest userRegisterRequest) {
        UserBase userBase = new UserBase();
        BeanUtils.copyProperties(userRegisterRequest, userBase);
        userBase.setCreateTime(new Date());
        userBase.setEnableStatus(1);

        // Check if there are users with the same email address
        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andEmailEqualTo(userBase.getEmail());
        List<UserBase> sameUserList = userBaseMapper.selectByExample(example);
        if (sameUserList.size() > 0) {
            return null;
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(userBase.getPassword());
        userBase.setPassword(encodedPassword);
        // Set uuid
        userBase.setId(UUID.randomUUID().toString());
        userBaseMapper.insert(userBase);
        return userBase;
    }

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        String token = null;
        try {
            UserDetails targetUser = getUserByUsername(userLoginRequest.getEmail());
            if (!passwordEncoder.matches(userLoginRequest.getPassword(), targetUser.getPassword())) {
                Asserts.fail("Incorrect username or password");
            }
            if(!targetUser.isEnabled()){
                Asserts.fail("Account has been disabled");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    targetUser,
                    null,
                    targetUser.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(targetUser);
        } catch (AuthenticationException e) {
            log.warn("Login Exception: {}", e.getMessage());
        }
        return token;
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        UserBase user = getUserFromCache(username);
        if (user != null) {
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("Incorrect username or password");
    }

    @Override
    public CacheService getCacheService() {
        return SpringUtil.getBean(CacheService.class);
    }

    @Override
    public UserBase getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        return customUserDetails.getUserBase();
    }
}
