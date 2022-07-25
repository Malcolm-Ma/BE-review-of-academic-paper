package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * User Register Request
 * @author Mingze Ma
 */
@Data
public class UserRegisterRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String title;

    @ApiModelProperty(value = "Avatar URL")
    private String avatar;

    @NotEmpty
    private String password;

    @NotNull
    @ApiModelProperty(value = "First name")
    private String firstName;

    @NotEmpty
    @ApiModelProperty(value = "Last name")
    private String lastName;

}
