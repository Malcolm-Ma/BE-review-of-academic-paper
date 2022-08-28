package com.apex.app.domain.bo;

import com.apex.app.domain.model.ReviewTaskOverall;
import com.apex.app.domain.model.SubmissionBase;
import lombok.Data;

/**
 * @author Mingze Ma
 */
@Data
public class ReviewTaskInfoBo extends ReviewTaskOverall {

    private SubmissionBase submissionInfo;

}
