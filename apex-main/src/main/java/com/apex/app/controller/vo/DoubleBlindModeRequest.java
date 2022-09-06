package com.apex.app.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Mingze Ma
 */
@Data
public class DoubleBlindModeRequest {
    @NotEmpty
    private String orgId;

    @NotNull
    private Boolean status;
}
