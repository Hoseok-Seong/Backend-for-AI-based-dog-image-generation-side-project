package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.global.config.ModelsLabConfig;
import com.example.puppicasso.global.config.OpenAIConfig;
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

@Service
@RequiredArgsConstructor
public class AIService {

    private final OpenAIConfig openAIConfig;
    private final ModelsLabConfig modelsLabConfig;

    @Transactional
    public String generateOpenAIImages(File imageFile, String prompt) throws IOException {
        HttpHeaders headers = openAIConfig.httpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(imageFile));
        body.add("model", "dall-e-2");
        body.add("prompt", prompt);
        body.add("n", 1);
        body.add("size", "512x512");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send request
        ResponseEntity<String> response = openAIConfig.restTemplate()
                .exchange(
                        "https://api.openai.com/v1/images/edits",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        return response.getBody();
    }

    @Transactional
    public String generateModelsLabImages(File imageFile, String prompt) throws IOException {
        HttpHeaders headers = modelsLabConfig.httpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(imageFile));
        body.add("model", "dall-e-2");
        body.add("prompt", prompt);
        body.add("n", 1);
        body.add("size", "512x512");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send request
        ResponseEntity<String> response = modelsLabConfig.restTemplate()
                .exchange(
                        "https://modelslab.com/api/v3/img2img",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        return response.getBody();
    }
}
