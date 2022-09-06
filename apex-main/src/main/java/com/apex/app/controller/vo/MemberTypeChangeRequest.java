package com.apex.app.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Mingze Ma
 */
@Data
public class MemberTypeChangeRequest {

    @NotEmpty
    private String orgId;

    @NotEmpty
    private String userId;

    @NotNull
    private Integer newType;
}
