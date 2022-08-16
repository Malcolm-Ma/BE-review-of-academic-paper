package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.OrgCreateRequest;
import com.apex.app.controller.vo.OrgInfoUpdateRequest;
import com.apex.app.controller.vo.OrgSetMemberRequest;
import com.apex.app.controller.vo.OrgSetMemberResponse;
import com.apex.app.domain.bo.OrgInfoBo;
import com.apex.app.domain.bo.OrgListByUserBo;
import com.apex.app.domain.bo.OrgMemberBo;
import com.apex.app.domain.model.OrgBase;
import com.apex.app.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Organization Controller
 *
 * @author Mingze Ma
 */
@Api(tags = "Organization Management Controller")
@RestController
@RequestMapping("/org")
public class OrganizationController {

    @Autowired
    OrgService orgService;

    @ApiOperation("Create organization")
    @PostMapping("/create")
    @ResponseBody
    public CommonResult<OrgBase> createOrg(@Validated @RequestBody OrgCreateRequest orgCreateRequest) {
        OrgBase orgBase = orgService.create(orgCreateRequest);
        return CommonResult.success(orgBase);
    }

    @ApiOperation("Update organization basic information")
    @PostMapping("/info/update")
    @ResponseBody
    public CommonResult<OrgBase> updateInfo(@Validated @RequestBody OrgInfoUpdateRequest orgInfoUpdateRequest) {
        OrgBase orgBase = orgService.updateInfo(orgInfoUpdateRequest);
        return CommonResult.success(orgBase);
    }

    @ApiOperation("Get member list in an organization")
    @GetMapping("/member_list/get")
    @ResponseBody
    public CommonResult<List<OrgMemberBo>> getMemberList(
            @ApiParam("Organization id for query")
            @Validated
            @RequestParam("org_id")
            @NotEmpty
            String orgId
    ) {
        List<OrgMemberBo> orgMemberBoList = orgService.getMemberListById(orgId);
        return CommonResult.success(orgMemberBoList);
    }

    @ApiOperation("Get organization list")
    @GetMapping("/list/get")
    @ResponseBody
    public CommonResult<List<OrgListByUserBo>> getOrgListByUser(
            @ApiParam("User id for query")
            @Validated
            @RequestParam("user_id")
            @NotEmpty
            String userId
    ) {
        List<OrgListByUserBo> resultList = orgService.getOrgListByUser(userId);
        return CommonResult.success(resultList);
    }

    @ApiOperation("Get organization details")
    @GetMapping("/detail/get")
    @ResponseBody
    public CommonResult<OrgInfoBo> getOrgDetail(
            @ApiParam("Org id for query")
            @Validated
            @RequestParam("org_id")
            @NotEmpty
            String orgId
    ) {
        OrgInfoBo result = orgService.getOrgDetail(orgId);
        return CommonResult.success(result);
    }

    @ApiOperation("Set members into an organization")
    @PostMapping("/member/set")
    @ResponseBody
    public CommonResult<OrgSetMemberResponse> setOrgMembers(
            @Validated
            @RequestBody
            OrgSetMemberRequest orgSetMemberRequest
    ) {
        OrgSetMemberResponse response = orgService.setOrgMembers(orgSetMemberRequest);
        return CommonResult.success(response);
    }

    @ApiOperation("Get submission count")
    @GetMapping("/submission_count/get")
    @ResponseBody
    public CommonResult<Map<String, Integer>> getSubmissionCount(
            @ApiParam("Org id for query")
            @Validated
            @RequestParam("org_id")
            @NotEmpty
            String orgId
    ) {
        Integer counts = orgService.getSubmissionCount(orgId);
        Map<String, Integer> result = new HashMap<>();
        result.put("count", counts);
        return CommonResult.success(result);
    }

}
