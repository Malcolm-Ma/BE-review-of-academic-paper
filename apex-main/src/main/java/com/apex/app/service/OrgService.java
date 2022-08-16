package com.apex.app.service;

import com.apex.app.controller.vo.OrgCreateRequest;
import com.apex.app.controller.vo.OrgInfoUpdateRequest;
import com.apex.app.controller.vo.OrgSetMemberRequest;
import com.apex.app.controller.vo.OrgSetMemberResponse;
import com.apex.app.domain.bo.OrgInfoBo;
import com.apex.app.domain.bo.OrgListByUserBo;
import com.apex.app.domain.bo.OrgMemberBo;
import com.apex.app.domain.model.OrgBase;

import java.util.List;

/**
 * Organization service interface
 *
 * @author Mingze Ma
 */
public interface OrgService {

    /**
     * Create org
     *
     * @param request OrgCreateRequest
     * @return created org basic info
     */
    OrgBase create(OrgCreateRequest request);

    /**
     * update basic information of org
     *
     * @param request OrgInfoUpdateRequest
     * @return updated org basic info
     */
    OrgBase updateInfo(OrgInfoUpdateRequest request);

    /**
     * Get member list by org id
     * @param orgId org id
     * @return member list
     */
    List<OrgMemberBo> getMemberListById(String orgId);

    /**
     * get Org List By User id
     * @param userId user id
     * @return org list
     */
    List<OrgListByUserBo> getOrgListByUser(String userId);

    /**
     * Get detail information of a single org
     * @param orgId org id
     * @return detail information bo
     */
    OrgInfoBo getOrgDetail(String orgId);

    OrgSetMemberResponse setOrgMembers(OrgSetMemberRequest request);

    Integer getSubmissionCount(String orgId);

}
