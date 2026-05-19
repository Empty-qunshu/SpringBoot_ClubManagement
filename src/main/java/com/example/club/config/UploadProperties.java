package com.example.club.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = "club.upload")
public class UploadProperties {
    private String path = "uploads";
    private long maxImageSize = 5L * 1024 * 1024;
    private Set<String> allowedExtensions = Set.of(".jpg", ".jpeg", ".png", ".gif", ".webp");
    private Set<String> allowedContentTypes = Set.of("image/jpeg", "image/png", "image/gif", "image/webp");
}
