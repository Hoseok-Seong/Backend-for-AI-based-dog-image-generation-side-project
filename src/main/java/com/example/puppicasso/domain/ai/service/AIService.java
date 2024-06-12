package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.global.config.ModelsLabConfig;
import com.example.puppicasso.global.config.OpenAIConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {

    private final OpenAIConfig openAIConfig;
    private final ModelsLabConfig modelsLabConfig;

    @Transactional
    public String generateOpenAIImages(File imageFile, String prompt) throws IOException {
        HttpHeaders headers = openAIConfig.openAIHttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(imageFile));
        body.add("model", "dall-e-2");
        body.add("prompt", prompt);
        body.add("n", 1);
        body.add("size", "512x512");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send request
        ResponseEntity<String> response = openAIConfig.openAIRestTemplate()
                .exchange(
                        "https://api.openai.com/v1/images/edits",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        return response.getBody();
    }

    @Transactional
    public String generateModelsLabImages(String imageUrl, String prompt) throws IOException {
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
