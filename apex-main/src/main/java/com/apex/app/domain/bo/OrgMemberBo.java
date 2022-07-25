package com.apex.app.domain.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Org member information
 *
 * @author Mingze Ma
 */
@Data
public class OrgMemberBo {

    private String id;

    @ApiModelProperty(value = "Full name")
    private String fullName;

    private String email;

    private String title;

    private String avatar;

    @ApiModelProperty(value = "Account enable status: 0 -> disabled; 1 -> enabled")
    private Integer enableStatus;

    @ApiModelProperty(value = "First name")
    private String firstName;

    @ApiModelProperty(value = "Last name")
    private String lastName;

    @ApiModelProperty(value = "Relationship type: 0->disabled; 1->member; 2->manager")
    private Integer type;

    private Date memberSince;

}
