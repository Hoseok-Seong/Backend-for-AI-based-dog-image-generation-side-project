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

    private void createUploadDirIfNotExist() {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (Files.notExists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String saveFile(final MultipartFile file) {
        createUploadDirIfNotExist();
        String fileName = System.currentTimeMillis() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath.toString();
    }

    public String getFileUrl(final String filePath) {
        return "/uploads/" + new File(filePath).getName();
    }

    public void deleteFile(final String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String saveFileAndGetFileUrl(final MultipartFile file) {
        createUploadDirIfNotExist();
        String fileName = System.currentTimeMillis() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "/uploads/" + new File(filePath.toString()).getName();
    }
}
