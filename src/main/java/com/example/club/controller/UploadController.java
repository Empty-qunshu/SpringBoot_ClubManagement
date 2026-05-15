package com.example.club.controller;

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
import java.util.Set;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/upload")
public class UploadController {
    private static final Path UPLOAD_DIR = Paths.get("uploads").toAbsolutePath().normalize();
    private static final long MAX_IMAGE_SIZE = 5L * 1024 * 1024;
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".gif", ".webp");
    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_GIF_VALUE,
            "image/webp"
    );

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result image(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return Result.error("请选择要上传的图片");
        }
        if (file.getSize() > MAX_IMAGE_SIZE) {
            return Result.error("图片大小不能超过 5MB");
        }
        String originalName = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
        String suffix = getSuffix(originalName);
        String contentType = file.getContentType();
        if (!ALLOWED_EXTENSIONS.contains(suffix) || contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            return Result.error("仅支持 jpg、png、gif、webp 图片");
        }
        Files.createDirectories(UPLOAD_DIR);
        String fileName = UUID.randomUUID() + suffix;
        Path target = UPLOAD_DIR.resolve(fileName).normalize();
        if (!target.startsWith(UPLOAD_DIR)) {
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
