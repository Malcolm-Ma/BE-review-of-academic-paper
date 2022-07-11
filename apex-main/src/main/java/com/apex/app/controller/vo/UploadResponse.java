package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * File uploading response
 * @author Mingze Ma
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UploadResponse {

    @ApiModelProperty("File Access URL")
    private String url;

    @ApiModelProperty("Filename")
    private String name;

}
