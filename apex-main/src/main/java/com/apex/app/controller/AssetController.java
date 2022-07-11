package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.UploadResponse;
import com.apex.app.service.AssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author Mingze Ma
 */
@Api(tags = "File Object Storage Controller")
@RestController
@RequestMapping("/file")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @ApiOperation("File uploading")
    @PostMapping("/upload")
    @ResponseBody
    public CommonResult<UploadResponse> upload(@Validated @RequestParam("file") MultipartFile file) {
        UploadResponse response = assetService.upload(file);
        return CommonResult.success(response);
    }

}
