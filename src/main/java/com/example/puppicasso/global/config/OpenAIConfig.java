package com.example.puppicasso.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * @author : hsseong
 * @apiNote  : ChatGPT에서 사용하는 환경 구성
 * @since : 2024/06/04
 */
@Configuration
public class OpenAIConfig {
    private static final String secretKey = System.getenv("PUPPICASSO_API_KEY");

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        return headers;
    }
}
