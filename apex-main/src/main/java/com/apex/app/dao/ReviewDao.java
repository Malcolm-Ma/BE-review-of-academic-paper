package com.apex.app.dao;

import com.apex.app.domain.bo.*;
import com.apex.app.domain.model.PaperAllocation;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Mingze Ma
 */
@Repository
public interface ReviewDao {

    Integer getSubmissionCount(@Param("org_id") String orgId);

    List<String> getSubmissionIdList(@Param("org_id") String orgId);

    void insertPaperAllocation(List<PaperAllocation> allocationList);

    void deleteAllocationByOrgId(@Param("org_id") String orgId);

    @MapKey("submissionId")
    Map<String, PaperAllocationMapBo> getAllocationResult(@Param("org_id") String orgId);

    List<ReviewTaskInfoBo> getReviewTask(@Param("org_id") String orgId, @Param("user_id") String userId, @Param("review_id") String reviewId);

    ReviewSummaryBo getReviewSummary(@Param("review_id") String reviewId, @Param("blind_mode") Boolean mode);

    List<UserDisplayBo> getConflictInterestUsers(@Param("submission_id") String submissionId);

    EvaluationCountsBo getEvaluationCounts(@Param("review_id") String reviewId);

}
