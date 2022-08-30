package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Mingze Ma
 */
@Data
public class GetReviewTaskRequest {

    @NotEmpty
    private String orgId;

    private String userId;

    @ApiModelProperty(value = "For getting specific review record")
    private String reviewId;

    private boolean adminView;

    public GetReviewTaskRequest() {
        this.adminView = false;
    }
}
