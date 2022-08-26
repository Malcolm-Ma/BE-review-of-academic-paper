package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.*;
import com.apex.app.domain.bo.ReviewTaskOverallBo;
import com.apex.app.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Review Controller
 *
 * @author Mingze Ma
 */
@Api(tags = "Reviewing Paper Controller")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @ApiOperation("Create review task")
    @PostMapping("/submission/create")
    @ResponseBody
    public CommonResult<ReviewTaskOverallBo> createReviewTask(@Validated @RequestBody ReviewCreateRequest request) {
        ReviewTaskOverallBo result = reviewService.createReviewTask(request);
        if (result == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(result);
    }

    @ApiOperation("Get submission list")
    @PostMapping("/submission_list/get")
    @ResponseBody
    public CommonResult<List<ReviewTaskOverallBo>> getSubmissionList(@Validated @RequestBody SubmissionListRequest request) {
        List<ReviewTaskOverallBo> result = reviewService.getSubmissionList(request);
        if (result == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(result);
    }

    @ApiOperation("Submit user bidding")
    @PostMapping("/bidding/pref/set")
    @ResponseBody
    public CommonResult SetBidding(@Validated @RequestBody SetBiddingRequest request) {
        boolean res = reviewService.setBiddingPref(request);
        return res ? CommonResult.success(null) : CommonResult.failed();
    }

    @ApiOperation("Get preference bidding summary")
    @GetMapping("/bidding/pref/summary")
    @ResponseBody
    public CommonResult<BiddingPrefSummaryResponse> getBiddingPrefSummary(
            @ApiParam("User id for query")
            @Validated
            @RequestParam("user_id")
            @NotEmpty
            String userId,
            @ApiParam("Org id for query")
            @Validated
            @RequestParam("org_id")
            @NotEmpty
            String orgId
    ) {
        BiddingPrefSummaryResponse res = reviewService.getBiddingPrefSummary(userId, orgId);
        return CommonResult.success(res);
    }

    @ApiOperation("Make allocation of review based on paper bidding")
    @PostMapping("/bidding/allocate")
    @ResponseBody
    public CommonResult<AllocateBiddingResponse> allocateBidding(@Validated @RequestBody AllocateBiddingRequest request) {
        AllocateBiddingResponse response = reviewService.allocateBidding(request);
        if (response == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(response);
    }
}
