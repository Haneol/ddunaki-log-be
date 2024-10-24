package com.blog.global.api;

import com.blog.global.service.S3Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final S3Upload s3Upload;

    @PostMapping("/api/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("images") MultipartFile multipartFile) throws IOException {

        long fileSize = multipartFile.getSize();

        String uploadedFileUrl = s3Upload.upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), fileSize);

        return new ResponseEntity<>(uploadedFileUrl, HttpStatus.CREATED);
    }
}
