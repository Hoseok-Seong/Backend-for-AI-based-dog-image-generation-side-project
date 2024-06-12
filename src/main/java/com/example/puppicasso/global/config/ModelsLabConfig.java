package com.example.puppicasso.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ModelsLabConfig {
    private static final String secretKey = System.getenv("PUPPICASSO_MODELSLAB_KEY");

    @Bean
    public RestTemplate modelsLabRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public HttpHeaders modelsLabHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
