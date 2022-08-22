package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.ReviewCreateRequest;
import com.apex.app.controller.vo.SetBiddingRequest;
import com.apex.app.controller.vo.SubmissionListRequest;
import com.apex.app.domain.bo.ReviewTaskOverallBo;
import com.apex.app.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
