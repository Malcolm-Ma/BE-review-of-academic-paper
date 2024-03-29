package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.*;
import com.apex.app.domain.bo.*;
import com.apex.app.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("Get the paper allocation result")
    @GetMapping("/allocation_result/get")
    @ResponseBody
    public CommonResult getBiddingResult(
            @ApiParam("Org id for query")
            @Validated
            @RequestParam("org_id")
            @NotEmpty
            String orgId
    ) {
        Map<String, PaperAllocationMapBo> res = reviewService.getAllocationResult(orgId);
        return CommonResult.success(res);
    }

    @ApiOperation("Get review task")
    @PostMapping("/task/get")
    @ResponseBody
    public CommonResult<List<ReviewTaskInfoBo>> getReviewTaskByUser(@Validated @RequestBody GetReviewTaskRequest request) {
        List<ReviewTaskInfoBo> response = reviewService.getReviewTask(request);
        if (response == null) {
            return CommonResult.failed("Invalid org_id or user_id");
        }
        return CommonResult.success(response);
    }

    @ApiOperation("Submit review evaluation")
    @PostMapping("/create")
    @ResponseBody
    public CommonResult submitNewReview(@Validated @RequestBody NewReviewRequest request) {

        Boolean response = reviewService.createNewReview(request);
        return CommonResult.success(response);
    }

    @ApiOperation("Get review summary")
    @PostMapping("/summary/get")
    @ResponseBody
    public CommonResult<ReviewSummaryBo> gerReviewSummary(@Validated @RequestBody GetReviewTaskRequest request) {
        if (request.isAdminView()) {
            return null;
        }
        ReviewSummaryBo response = reviewService.getReviewSummary(request);
        if (response == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(response);
    }

    @ApiOperation("Get user of conflict of interest")
    @GetMapping("/conflict_interest/get")
    @ResponseBody
    public CommonResult<List<UserDisplayBo>> getConflictInterestUsers(
            @ApiParam("Submission id for query")
            @Validated
            @RequestParam("submission_id")
            @NotEmpty
            String submission
    ) {
        List<UserDisplayBo> result = reviewService.getConflictInterestUsers(submission);
        return CommonResult.success(result);
    }

    @ApiOperation("Submit review comment")
    @PostMapping("/comment/create")
    @ResponseBody
    public CommonResult createReviewComment(@Validated @RequestBody CreateCommentRequest request) {

        Boolean response = reviewService.createComment(request);
        return CommonResult.success(response);
    }

    @ApiOperation("Generate paper reviewing result")
    @PostMapping("/result/generate")
    @ResponseBody
    public CommonResult<Boolean> generateReviewingResult(@Validated @RequestBody GenerateResultRequest request) {

        Boolean response = reviewService.generateReviewingResult(request);
        return CommonResult.success(response);
    }

    @ApiOperation("Revise a review")
    @PostMapping("/revise")
    @ResponseBody
    public CommonResult<Boolean> reviseReview(@Validated @RequestBody ReviseReviewRequest request) {
        Boolean response = reviewService.reviseReview(request);
        return CommonResult.success(response);
    }
}
