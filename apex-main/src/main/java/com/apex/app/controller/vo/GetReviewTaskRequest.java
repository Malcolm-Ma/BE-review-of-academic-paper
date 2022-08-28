package com.apex.app.controller.vo;

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

    private boolean adminView;

    public GetReviewTaskRequest() {
        this.adminView = false;
    }
}
