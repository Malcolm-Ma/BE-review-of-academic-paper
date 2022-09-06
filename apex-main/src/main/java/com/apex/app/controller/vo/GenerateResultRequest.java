package com.apex.app.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Mingze Ma
 */
@Data
public class GenerateResultRequest {

    @NotEmpty
    private String orgId;

    private String reviewId;
}
