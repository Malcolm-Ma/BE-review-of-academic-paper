package com.apex.app.service;

import com.apex.app.controller.vo.*;
import com.apex.app.domain.bo.PaperAllocationMapBo;
import com.apex.app.domain.bo.ReviewTaskOverallBo;

import java.util.List;
import java.util.Map;

/**
 * Core paper reviewing service interface
 *
 * @author Mingze Ma
 */
public interface ReviewService {

    ReviewTaskOverallBo createReviewTask(ReviewCreateRequest request);

    List<ReviewTaskOverallBo> getSubmissionList(SubmissionListRequest request);

    boolean setBiddingPref(SetBiddingRequest request);

    BiddingPrefSummaryResponse getBiddingPrefSummary(String userId, String orgId);

    AllocateBiddingResponse allocateBidding(AllocateBiddingRequest request);

    Map<String, PaperAllocationMapBo> getAllocationResult(String orgId);

}
