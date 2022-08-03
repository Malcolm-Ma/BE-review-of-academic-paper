package com.apex.app.service;

import com.apex.app.controller.vo.ReviewCreateRequest;
import com.apex.app.domain.bo.ReviewTaskOverallBo;

import java.util.List;

/**
 * Core paper reviewing service interface
 *
 * @author Mingze Ma
 */
public interface ReviewService {

    ReviewTaskOverallBo createReviewTask(ReviewCreateRequest request);

    List<ReviewTaskOverallBo> getSubmission(String orgId);

}
