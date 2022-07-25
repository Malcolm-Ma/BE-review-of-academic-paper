package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Create org Request
 *
 * @author Mingze Ma
 */
@Data
public class OrgCreateRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    private String description;

    @ApiModelProperty("Initial user id added in the organization")
    private List<String> userIdList;

}
