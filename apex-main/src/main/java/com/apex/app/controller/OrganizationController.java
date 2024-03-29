package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.*;
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
import javax.validation.constraints.NotNull;
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

    @ApiOperation("Change to forward the reviewing process")
    @PostMapping("/process/change")
    @ResponseBody
    public CommonResult<ChangeOrgProcessResponse> changeReviewProcess(
            @Validated
            @RequestBody
            ChangeOrgProcessRequest request
    ) {
        ChangeOrgProcessResponse res = orgService.changeReviewProcess(request);
        if (res == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(res);
    }

    @ApiOperation("check User belonging")
    @GetMapping("/belong/check")
    @ResponseBody
    public CommonResult<Boolean> checkUserBelonging(
            @ApiParam("Org id")
            @Validated
            @RequestParam("org_id")
            @NotEmpty
            String orgId,
            @ApiParam("User id")
            @Validated
            @RequestParam("user_id")
            String userId
    ) {
        Boolean res = orgService.checkUserBelonging(orgId, userId);
        return CommonResult.success(res);
    }

    @ApiOperation("Set double-blind mode")
    @PostMapping("/double_blind/set")
    @ResponseBody
    public CommonResult setDoubleBlind(
            @Validated
            @RequestBody
            DoubleBlindModeRequest request
    ) {
        Boolean res = orgService.setDoubleBlindMode(request.getOrgId(), request.getStatus());
        Map<String, Boolean> result = new HashMap<>();
        result.put("current_mode", res);
        if (res != null) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation("Change member type")
    @PostMapping("/member/change")
    @ResponseBody
    public CommonResult changeMemberType(
            @Validated
            @RequestBody
            MemberTypeChangeRequest request
    ) {
        Integer res = orgService.changeMemberType(request);
        Map<String, Integer> result = new HashMap<>();
        result.put("current_type", res);
        if (res != null) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

}
