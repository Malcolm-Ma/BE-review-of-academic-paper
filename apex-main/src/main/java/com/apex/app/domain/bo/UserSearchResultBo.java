package com.apex.app.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * User searching result
 *
 * @author Mingze Ma
 */
@Data
public class UserSearchResultBo {

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

    @JsonIgnore
    private String orgIds;

    @ApiModelProperty(value = "Org id list that user belongs to")
    private List<String> orgIdList;

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
        this.orgIdList = Arrays.asList(orgIds.split(","));
    }
}
