package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * User Login Request param
 * @author Mingze Ma
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginRequest {

    @NotEmpty
    @ApiModelProperty(value = "Email / Username",required = true)
    private String email;

    @NotEmpty
    @ApiModelProperty(value = "Password",required = true)
    private String password;

}
