package com.apex.app.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Create Review Request
 *
 * @author Mingze Ma
 */
@Data
public class ReviewCreateRequest {

    private String userId;

    @NotEmpty
    private String orgId;

    private Date deadline;

    @NotEmpty
    private String title;

    @NotEmpty
    private String authors;

    @NotEmpty
    private String abstracts;

    private String keywords;

    @NotEmpty
    private String contactEmail;

    @NotNull
    private Date publishedTime;

    @NotEmpty
    private String resourceUrl;

}
