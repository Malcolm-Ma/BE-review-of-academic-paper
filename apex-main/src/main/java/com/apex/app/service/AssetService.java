package com.apex.app.service;

import com.apex.app.controller.vo.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Asset(files) Management Service
 * @author Mingze Ma
 */
public interface AssetService {

    /**
     * Upload files
     */
    UploadResponse upload(MultipartFile file);

}
