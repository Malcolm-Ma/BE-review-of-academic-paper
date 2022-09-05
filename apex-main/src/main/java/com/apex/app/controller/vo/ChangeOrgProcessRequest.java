package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author Mingze Ma
 */
@Data
public class ChangeOrgProcessRequest {

    @NotEmpty
    @ApiModelProperty(required = true)
    private String orgId;

    private Date submissionDdl;

    private Date biddingDdl;

    private Date reviewingDdl;
}
