package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Mingze Ma
 */
@Data
public class AllocateBiddingResponse {

    private String orgId;

    private Integer reviewDemand;

    private Integer minTaskPerUser;
}
