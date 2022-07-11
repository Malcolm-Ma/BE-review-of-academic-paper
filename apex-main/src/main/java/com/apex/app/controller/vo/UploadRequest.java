package com.apex.app.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author Mingze Ma
 */
@Data
public class UploadRequest {

    @NotEmpty
    @ApiModelProperty(value = "File", required = true)
    MultipartFile file;

}
