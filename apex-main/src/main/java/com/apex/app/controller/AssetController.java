package com.apex.app.controller;

import com.apex.app.common.api.CommonResult;
import com.apex.app.controller.vo.UploadResponse;
import io.minio.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Mingze Ma
 */
@Api(tags = "File Object Storage Controller")
@RestController
@RequestMapping("/file")
public class AssetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetController.class);

    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Value("${minio.bucketName}")
    private String BUCKET_NAME;

    @Value("${minio.accessKey}")
    private String ACCESS_KEY;

    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @ApiOperation("File uploading")
    @PostMapping("/upload")
    @ResponseBody
    public CommonResult<UploadResponse> upload(@RequestPart("file") MultipartFile file) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(ACCESS_KEY, SECRET_KEY)
                    .build();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (isExist) {
                LOGGER.info("Bucket {} already exists.", BUCKET_NAME);
            } else {
                // Make a new bucket called BUCKET_NAME.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            }

            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            // Set name to storage object
            String objectName = sdf.format(new Date()) + "/" + filename;

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(objectName)
                            .contentType(file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE)
                            .build()
            );
            LOGGER.info("File uploading successfully");
            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse.setName(filename);
            uploadResponse.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            return CommonResult.success(uploadResponse);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error in uploading: {}！", e.getMessage());
        }
        return CommonResult.failed("File uploading error");
    }

}
