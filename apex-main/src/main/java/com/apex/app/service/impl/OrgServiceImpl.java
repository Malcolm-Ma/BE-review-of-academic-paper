package com.apex.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.apex.app.common.exception.Asserts;
import com.apex.app.controller.vo.OrgCreateRequest;
import com.apex.app.controller.vo.OrgInfoUpdateRequest;
import com.apex.app.controller.vo.OrgSetMemberRequest;
import com.apex.app.controller.vo.OrgSetMemberResponse;
import com.apex.app.dao.OrgDao;
import com.apex.app.domain.bo.OrgInfoBo;
import com.apex.app.domain.bo.OrgListByUserBo;
import com.apex.app.domain.bo.OrgMemberBo;
import com.apex.app.domain.model.*;
import com.apex.app.domain.type.UserTypeEnum;
import com.apex.app.mapper.OrgBaseMapper;
import com.apex.app.mapper.UserBaseMapper;
import com.apex.app.mapper.UserOrgMergeMapper;
import com.apex.app.service.OrgService;
import com.apex.app.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Organization Service implementation
 *
 * @author Mingze Ma
 */
@Service
@Slf4j
public class OrgServiceImpl implements OrgService {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    OrgBaseMapper orgBaseMapper;

    @Autowired
    UserOrgMergeMapper userOrgMergeMapper;

    @Autowired
    UserBaseMapper userBaseMapper;

    @Autowired
    OrgDao orgDao;

    @Override
    public OrgBase create(OrgCreateRequest orgCreateRequest) {
        OrgBase orgBase = new OrgBase();
        BeanUtils.copyProperties(orgCreateRequest, orgBase);
        orgBase.setCreateTime(new Date());
        orgBase.setActiveStatus(1);

        // Check if there are orgs with the same email address
        OrgBaseExample example = new OrgBaseExample();
        example.createCriteria().andEmailEqualTo(orgBase.getEmail()).andNameEqualTo(orgBase.getName());
        List<OrgBase> resultList = orgBaseMapper.selectByExample(example);
        if (resultList.size() > 0) {
            Asserts.fail("Organizations with the same name and email already exist");
        }
        orgBase.setId(UUID.randomUUID().toString());
        // TODO: set initial user id list
        orgBaseMapper.insert(orgBase);
        // Add relationship of current user and org
        UserBase currentUser = userAuthService.getCurrentUser();
        UserOrgMerge userOrgMerge = new UserOrgMerge();
        userOrgMerge.setUserId(currentUser.getId());
        userOrgMerge.setOrgId(orgBase.getId());
        userOrgMerge.setType(UserTypeEnum.OWNER.getValue());
        userOrgMerge.setCreateTime(new Date());
        userOrgMergeMapper.insert(userOrgMerge);
        log.info("Create org: {}", orgBase);

        // Set users if applicable
        List<String> userIdList = orgCreateRequest.getUserIdList();
        if (userIdList != null && userIdList.size() > 0) {
            OrgSetMemberRequest orgSetMemberRequest = new OrgSetMemberRequest();
            orgSetMemberRequest.setOrgId(orgBase.getId());
            orgSetMemberRequest.setIndexType("id");
            orgSetMemberRequest.setUserType(UserTypeEnum.MEMBER);
            orgSetMemberRequest.setIndexList(userIdList);
            setOrgMembers(orgSetMemberRequest);
        }

        return orgBase;
    }

    @Override
    public OrgBase updateInfo(OrgInfoUpdateRequest orgInfoUpdateRequest) {
        OrgBase targetOrg = orgBaseMapper.selectByPrimaryKey(orgInfoUpdateRequest.getId());
        BeanUtil.copyProperties(orgInfoUpdateRequest, targetOrg, CopyOptions.create().ignoreNullValue().ignoreError());
        orgBaseMapper.updateByPrimaryKey(targetOrg);
        log.info("Update org info: {}", targetOrg);

        return targetOrg;
    }

    @Override
    public List<OrgMemberBo> getMemberListById(String orgId) {
        return orgDao.getOrgMemberList(orgId);
    }

    @Override
    public List<OrgListByUserBo> getOrgListByUser(String userId) {
        return orgDao.getOrgListByUserId(userId);
    }

    @Override
    public OrgInfoBo getOrgDetail(String orgId) {
        OrgInfoBo orgInfo = new OrgInfoBo();
        BeanUtils.copyProperties(orgBaseMapper.selectByPrimaryKey(orgId), orgInfo);
        List<OrgMemberBo> allMemberList = orgDao.getOrgMemberList(orgId);
        orgInfo.appendMembers(allMemberList);

        return orgInfo;
    }

    @Override
    public OrgSetMemberResponse setOrgMembers(OrgSetMemberRequest request) {
        // Check orgId
        OrgBaseExample orgBaseExample = new OrgBaseExample();
        orgBaseExample.createCriteria().andIdEqualTo(request.getOrgId());
        List<OrgBase> orgResList = orgBaseMapper.selectByExample(orgBaseExample);
        if (orgResList.size() < 1) {
            Asserts.fail("Can not find org with id " + request.getOrgId());
            log.warn("Unable to find org {}", request.getOrgId());
        }
        // Check id and email
        if (request.getIndexType().equals("id") || request.getIndexType().equals("email")) {
            List<UserBase> userIdList = new ArrayList<>();
            Map<String, String> failureMap = new HashMap<>();
            if (request.getIndexType().equals("email")) {
                // Find user id by email
                UserBaseExample example = new UserBaseExample();
                example.createCriteria().andEmailIn(request.getIndexList());
                List<UserBase> userList = userBaseMapper.selectByExample(example);
                if (userList.size() != request.getIndexList().size()) {
                    for (String email : request.getIndexList()) {
                        if (userList.stream().noneMatch(e -> e.getEmail().equals(email))) {
                            failureMap.put(email, "Can not find any user");
                        }
                    }
                }
                for (UserBase u : userList) {
                    if (u.getEnableStatus() == 1) {
                        userIdList.add(u);
                        continue;
                    }
                    failureMap.put(u.getEmail(), "The user is disabled");
                }
            }
            if (request.getIndexType().equals("id")) {
                UserBaseExample example = new UserBaseExample();
                example.createCriteria().andIdIn(request.getIndexList());
                List<UserBase> userList = userBaseMapper.selectByExample(example);
                if (userList.size() != request.getIndexList().size()) {
                    for (String id : request.getIndexList()) {
                        if (userList.stream().noneMatch(e -> e.getId().equals(id))) {
                            failureMap.put(id, "Can not find the user");
                        }
                    }
                }
                for (UserBase u : userList) {
                    if (u.getEnableStatus() == 1) {
                        userIdList.add(u);
                        continue;
                    }
                    failureMap.put(u.getEmail(), "The user is disabled");
                }
            }
            // Check whether the user has been a member of the org
            UserOrgMergeExample example = new UserOrgMergeExample();
            example.createCriteria().andOrgIdEqualTo(request.getOrgId());
            List<UserOrgMerge> userOrgMergeList = userOrgMergeMapper.selectByExample(example);
            for (UserOrgMerge merge : userOrgMergeList) {
                Optional<UserBase> curUser = userIdList.stream()
                        .filter(e -> e.getId().equals(merge.getUserId())).findFirst();
                if (curUser.isPresent()) {
                    if (request.getIndexType().equals("email")) {
                        failureMap.put(curUser.get().getEmail(), "The user has already been in current org");
                    } else {
                        failureMap.put(curUser.get().getId(), "The user has already been in current org");
                    }
                    userIdList.remove(curUser.get());
                }
            }
            // Insert
            List<String> successList = new ArrayList<>();
            for (UserBase user : userIdList) {
                UserOrgMerge userOrgMerge = new UserOrgMerge();
                userOrgMerge.setOrgId(request.getOrgId());
                userOrgMerge.setUserId(user.getId());
                userOrgMerge.setType(request.getUserType().getValue());
                userOrgMerge.setCreateTime(new Date());
                userOrgMergeMapper.insertSelective(userOrgMerge);
                successList.add(request.getIndexType().equals("email") ? user.getEmail() : user.getId());
                log.info("Insert user:{} to org:{}", user.getId(), request.getOrgId());
            }

            OrgSetMemberResponse response = new OrgSetMemberResponse();
            response.setSuccessList(successList);
            response.setFailureList(failureMap);
            return response;
        }
        Asserts.fail("Invalid index type, only accepted 'id' or 'email'");
        return null;
    }
}
