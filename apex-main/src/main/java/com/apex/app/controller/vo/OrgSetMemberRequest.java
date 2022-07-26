package com.apex.app.controller.vo;

import com.apex.app.domain.type.UserTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Request of setting members into org
 *
 * @author Mingze Ma
 */
@Data
public class OrgSetMemberRequest {

    @NotEmpty
    @ApiModelProperty(value = "Target organization id'", required = true)
    private String orgId;

    @NotEmpty
    @ApiModelProperty(value = "Primary index type: 'id' or 'email'", required = true)
    private String indexType;

    @NotNull
    @ApiModelProperty(value = "User type: 0->disabled; 1->member; 2->manager", required = true)
    private UserTypeEnum userType;

    @NotEmpty
    @ApiModelProperty(value = "List of member's index", required = true)
    private List<String> indexList;

}
