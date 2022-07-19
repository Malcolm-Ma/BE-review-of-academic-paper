package com.apex.app.controller.vo;

import com.apex.app.domain.model.UserBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @author Mingze Ma
 */
@Data
public class UserInfoResponse {

    private String id;

    @ApiModelProperty(value = "Full name")
    private String name;

    private String email;

    private String title;

    private String avatar;

    @ApiModelProperty(value = "create time")
    private Date createTime;

    @ApiModelProperty(value = "Account enable status: 0 -> disabled; 1 -> enabled")
    private Integer enableStatus;

    public UserInfoResponse (UserBase userBase) {
        this.id = userBase.getId();
        this.name = userBase.getName();
        this.email = userBase.getEmail();
        this.avatar = userBase.getAvatar();
        this.createTime = userBase.getCreateTime();
        this.enableStatus = userBase.getEnableStatus();
        this.title = userBase.getTitle();
    }
}
