package com.example.puppicasso.domain.ai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class ImageFileService {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    public String saveFile(final MultipartFile file) throws IOException {
        createUploadDirIfNotExist();
        String fileName = System.currentTimeMillis() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), filePath);
        return filePath.toString();
    }

    private void createUploadDirIfNotExist() throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
    }

    public String getFileUrl(final String filePath) {
        return "/uploads/" + new File(filePath).getName();
    }

    public void deleteFile(final String filePath) throws IOException {
        Files.deleteIfExists(Paths.get(filePath));
    }

    public String saveFileAndGetFileUrl(final MultipartFile file) throws IOException {
        createUploadDirIfNotExist();
        String fileName = System.currentTimeMillis() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), filePath);
        return "/uploads/" + new File(filePath.toString()).getName();
    }
}
