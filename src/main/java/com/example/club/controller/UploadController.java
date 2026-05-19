package com.example.club.controller;

import com.example.club.config.UploadProperties;
import com.example.club.entity.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/upload")
public class UploadController {
    private final UploadProperties uploadProperties;

    public UploadController(UploadProperties uploadProperties) {
        this.uploadProperties = uploadProperties;
    }

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result image(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return Result.error("请选择要上传的图片");
        }
        if (file.getSize() > uploadProperties.getMaxImageSize()) {
            return Result.error("图片大小不能超过 5MB");
        }
        String originalName = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
        String suffix = getSuffix(originalName);
        String contentType = file.getContentType();
        if (!uploadProperties.getAllowedExtensions().contains(suffix)
                || contentType == null
                || !uploadProperties.getAllowedContentTypes().contains(contentType)) {
            return Result.error("仅支持 jpg、png、gif、webp 图片");
        }
        Path uploadDir = Paths.get(uploadProperties.getPath()).toAbsolutePath().normalize();
        Files.createDirectories(uploadDir);
        String fileName = UUID.randomUUID() + suffix;
        Path target = uploadDir.resolve(fileName).normalize();
        if (!target.startsWith(uploadDir)) {
            return Result.error("文件路径不合法");
        }
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return Result.success(Map.of(
                "url", "/uploads/" + fileName,
                "fileName", fileName,
                "originalName", originalName,
                "size", file.getSize()
        ));
    }

    private String getSuffix(String originalName) {
        int dotIndex = originalName.lastIndexOf('.');
        if (dotIndex < 0) {
            return "";
        }
        return originalName.substring(dotIndex).toLowerCase();
    }
}
