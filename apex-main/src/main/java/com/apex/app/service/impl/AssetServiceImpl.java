package com.apex.app.service.impl;

import com.apex.app.controller.vo.UploadResponse;
import com.apex.app.service.AssetService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Asset(file) Service Implementation
 * @author Mingze Ma
 */
@Service
@Slf4j
public class AssetServiceImpl implements AssetService {
    
    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Value("${minio.bucketName}")
    private String BUCKET_NAME;

    @Value("${minio.accessKey}")
    private String ACCESS_KEY;

    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @Override
    public UploadResponse upload(MultipartFile file) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(ACCESS_KEY, SECRET_KEY)
                    .build();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (isExist) {
                log.info("Bucket {} already exists.", BUCKET_NAME);
            } else {
                // Make a new bucket called BUCKET_NAME.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            }

            String filename = UUID.randomUUID().toString().split("-")[0] +
                    "-" +
                    new Date().getTime() +
                    "." +
                    FileNameUtils.getExtension(file.getOriginalFilename());
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
            log.info("File {} uploading successfully", objectName);
            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse.setName(filename);
            uploadResponse.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            return uploadResponse;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error in uploading: {}！", e.getMessage());
        }
        throw new RuntimeException("File uploading error");
    }

}
