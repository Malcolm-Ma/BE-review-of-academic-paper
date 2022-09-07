package com.apex.app.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/** ReviseReviewRequest
 * @author Mingze Ma
 */
@Data
public class ReviseReviewRequest {

    @NotEmpty
    private String reviewId;

    @NotNull
    private Long reviseEvaluationId;

}
