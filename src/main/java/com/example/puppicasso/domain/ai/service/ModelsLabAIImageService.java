package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.global.config.ModelsLabConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ModelsLabAIImageService {

    private final ModelsLabConfig modelsLabConfig;

    public String createModelsLabAIImage(String imageUrl, String prompt) throws IOException {
        HttpHeaders headers = modelsLabConfig.modelsLabHttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("key", modelsLabConfig.getSecretKey());
        requestBody.put("prompt", prompt);
        requestBody.put("negative_prompt", "bad quality");
        requestBody.put("init_image", imageUrl);
        requestBody.put("width", "512");
        requestBody.put("height", "512");
        requestBody.put("samples", "1");
        requestBody.put("temp", false);
        requestBody.put("safety_checker", false);
        requestBody.put("strength", 1.0);
        requestBody.put("seed", null);
        requestBody.put("enhance_prompt", true);
        requestBody.put("webhook", null);
        requestBody.put("track_id", null);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequestBody, headers);

        // Send request
        ResponseEntity<String> response = modelsLabConfig.modelsLabRestTemplate().postForEntity(
                "https://modelslab.com/api/v3/img2img",
                requestEntity,
                String.class
        );

        return response.getBody();
    }
}
