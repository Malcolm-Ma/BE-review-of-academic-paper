package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Mingze Ma
 */
@Data
public class AllocateBiddingRequest {

    @NotEmpty
    private String orgId;

    @NotNull
    @ApiModelProperty(value = "Demand of review times per paper", required = true)
    private Integer reviewDemand;

    @ApiModelProperty(value = "Minimum papers to review of a user (Has a potential limit)")
    private Integer minTaskPerUser;

}
