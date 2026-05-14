package com.example.club.controller;

import com.example.club.entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/upload")
public class UploadController {
    private static final Path UPLOAD_DIR = Paths.get("uploads");

    @PostMapping("/image")
    public Result image(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return Result.error("请选择要上传的图片");
        }
        String originalName = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
        String lowerName = originalName.toLowerCase();
        if (!(lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".png") || lowerName.endsWith(".gif") || lowerName.endsWith(".webp"))) {
            return Result.error("仅支持 jpg、png、gif、webp 图片");
        }
        Files.createDirectories(UPLOAD_DIR);
        String suffix = originalName.contains(".") ? originalName.substring(originalName.lastIndexOf('.')) : ".jpg";
        String fileName = UUID.randomUUID() + suffix;
        Path target = UPLOAD_DIR.resolve(fileName);
        file.transferTo(target.toFile());
        return Result.success(Map.of("url", "/uploads/" + fileName));
    }
}
