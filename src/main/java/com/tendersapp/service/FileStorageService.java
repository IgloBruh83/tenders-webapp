package com.tendersapp.service;

import jakarta.annotation.PostConstruct;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileStorageService {

    private final Path rootLocation = Paths.get("upload-dir");

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(rootLocation);
    }

    public String store(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "-" + StringUtils.cleanPath(file.getOriginalFilename());
        Path dest = rootLocation.resolve(filename);
        Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
        return "/files/" + filename;

    }
}
