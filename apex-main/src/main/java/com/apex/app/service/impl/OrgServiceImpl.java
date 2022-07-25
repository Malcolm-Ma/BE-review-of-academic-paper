package com.apex.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.apex.app.common.exception.Asserts;
import com.apex.app.controller.vo.OrgCreateRequest;
import com.apex.app.controller.vo.OrgInfoUpdateRequest;
import com.apex.app.dao.OrgDao;
import com.apex.app.domain.bo.OrgInfoBo;
import com.apex.app.domain.bo.OrgListByUserBo;
import com.apex.app.domain.bo.OrgMemberBo;
import com.apex.app.domain.model.OrgBase;
import com.apex.app.domain.model.OrgBaseExample;
import com.apex.app.domain.model.UserBase;
import com.apex.app.domain.model.UserOrgMerge;
import com.apex.app.mapper.OrgBaseMapper;
import com.apex.app.mapper.UserOrgMergeMapper;
import com.apex.app.service.OrgService;
import com.apex.app.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        userOrgMerge.setType(2);
        userOrgMerge.setCreateTime(new Date());
        userOrgMergeMapper.insert(userOrgMerge);

        log.info("Create org: {}", orgBase);

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
}
