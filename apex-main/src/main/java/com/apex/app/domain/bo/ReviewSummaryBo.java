package com.apex.app.domain.bo;

import com.apex.app.domain.model.ReviewEvaluation;
import com.apex.app.domain.model.ReviewTaskOverall;
import com.apex.app.domain.model.SubmissionBase;
import lombok.Data;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * Review summary BO
 * @author Mingze Ma
 */
@Data
public class ReviewSummaryBo extends ReviewTaskOverall {

    private SubmissionBase submissionInfo;

    private List<ReviewEvaluation> reviewEvaluationList;

    private List<UserDisplayBo> reviewerList;

}
