package com.apex.app.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Mingze Ma
 */
@Data
public class CreateCommentRequest {

    @NotEmpty
    private String reviewId;

    @NotEmpty
    private String comment;

}
