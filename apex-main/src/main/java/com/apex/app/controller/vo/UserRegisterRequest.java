package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * User Register Request
 * @author Mingze Ma
 */
@Data
public class UserRegisterRequest {

    @NotEmpty
    @ApiModelProperty(value = "Full name", required = true)
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "Email", required = true)
    private String email;

    @NotEmpty
    @ApiModelProperty(value = "User title", required = true)
    private String title;

    @ApiModelProperty(value = "Avatar url")
    private String avatar;

    @NotEmpty
    @ApiModelProperty(value = "Password", required = true)
    private String password;

}
