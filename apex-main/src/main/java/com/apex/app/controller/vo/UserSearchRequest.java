package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * User Search Request
 *
 * @author Mingze Ma
 */
@Data
public class UserSearchRequest {

    @NotNull
    @ApiModelProperty(value = "Search content", required = true)
    private String query;

    @ApiModelProperty(value = "Result count limit, default 20")
    private Integer limit;

}
