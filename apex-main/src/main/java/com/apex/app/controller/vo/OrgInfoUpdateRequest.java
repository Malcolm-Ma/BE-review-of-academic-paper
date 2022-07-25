package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Org Info Update Request
 *
 * @author Mingze Ma
 */
@Data
public class OrgInfoUpdateRequest {

    @ApiModelProperty(value = "Target org id", required = true)
    @NotEmpty
    private String id;

    private String name;

    private String email;

    private String description;

}
