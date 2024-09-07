package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.domain.ai.dto.S3ImageResp;
import com.example.puppicasso.domain.ai.exception.S3IoException;
import com.example.puppicasso.domain.ai.util.S3KeyGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;

@Service
public class S3FileService {
    private static final String BUCKET = System.getenv("AWS_S3_BUCKET");
    private final S3Operations s3Operations;

    public S3FileService(S3Operations s3Operations) {
        this.s3Operations = s3Operations;
    }

    public S3ImageResp saveFileAndGetFileUrl(final MultipartFile file)  {
        String key = S3KeyGenerator.makeKey(file);

        try (InputStream is = file.getInputStream()) {
            s3Operations.upload(BUCKET, key, is,
                    ObjectMetadata.builder().contentType(file.getContentType()).build());
        } catch(IOException e) {
            throw new S3IoException();
        }

        URL url = s3Operations.createSignedGetURL(BUCKET, key, Duration.ofSeconds(120));

        return S3ImageResp.builder().key(key).url(url.toString()).build();
    }

    public void deleteFile(final String key) {
        s3Operations.deleteObject(BUCKET, key);
    }
}
