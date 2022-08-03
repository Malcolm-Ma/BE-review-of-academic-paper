package com.apex.app.domain.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * User information for displaying Bo
 *
 * @author Mingze Ma
 */
@Data
public class UserDisplayBo {

    private String id;

    @ApiModelProperty(value = "Full name")
    private String fullName;

    private String email;

    private String title;

    private String avatar;

    @ApiModelProperty(value = "create time")
    private Date createTime;

    @ApiModelProperty(value = "Account enable status: 0 -> disabled; 1 -> enabled")
    private Integer enableStatus;

    @ApiModelProperty(value = "First name")
    private String firstName;

    @ApiModelProperty(value = "Last name")
    private String lastName;

}
