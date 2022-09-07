package com.apex.app.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Review Submission List Request
 *
 * @author Mingze Ma
 */
@Data
public class SubmissionListRequest {

    @NotEmpty
    private String orgId;

    private String scope;

}
