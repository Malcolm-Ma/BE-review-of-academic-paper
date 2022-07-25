package com.apex.app.domain.bo;

import com.apex.app.domain.model.OrgBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * org list selected by user
 *
 * @author Mingze Ma
 */
@Data
public class OrgListByUserBo extends OrgBase {

    @ApiModelProperty("User Type in an org")
    private Integer userType;

}
