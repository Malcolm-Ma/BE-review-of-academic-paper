package com.apex.app.service.impl;

import com.apex.app.controller.vo.UserLoginRequest;
import com.apex.app.controller.vo.UserRegisterRequest;
import com.apex.app.mapper.UserBaseMapper;
import com.apex.app.model.UserBase;
import com.apex.app.model.UserBaseExample;
import com.apex.app.service.UserAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
public class UserAuthServiceImpl implements UserAuthService {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    UserBaseMapper userBaseMapper;

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
//        String encodedPassword = passwordEncoder.encode(userBase.getPassword());
//        userBase.setPassword(encodedPassword);
        // Set uuid
        userBase.setId(UUID.randomUUID().toString());
        userBaseMapper.insert(userBase);
        return userBase;
    }

    @Override
    public UserBase login(UserLoginRequest userLoginRequest) {
        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andEmailEqualTo(userLoginRequest.getEmail());
        List<UserBase> targetUserList = userBaseMapper.selectByExample(example);
        if (targetUserList.size() == 0) {
            return null;
        }
        UserBase targetUser = targetUserList.get(0);
        if (Objects.equals(targetUser.getPassword(), userLoginRequest.getPassword())) {
            return targetUser;
        }
        return null;
    }

    @Override
    public UserDetails getUserByEmail(String email) {
        return null;
    }
}
