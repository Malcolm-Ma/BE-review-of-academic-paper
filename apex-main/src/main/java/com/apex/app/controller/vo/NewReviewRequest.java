package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Mingze Ma
 */
@Data
public class NewReviewRequest {

    @NotEmpty
    private String reviewId;

    @NotNull
    private Short overallEvaluation;

    @NotNull
    private Byte confidence;

    @NotNull
    @ApiModelProperty(value = "Accept as a short paper: 0->no; 1->yes")
    private Byte asShortPaper;

    @NotEmpty
    private String evaluationContent;

    private String confidenceRemark;

}
