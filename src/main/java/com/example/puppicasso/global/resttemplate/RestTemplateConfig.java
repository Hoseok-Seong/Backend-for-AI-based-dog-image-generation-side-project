package com.example.puppicasso.global.resttemplate;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate modelsLabTemplate() {
        return restTemplateBuilder.rootUri("https://modelslab.com/api/v3/img2img")
                .additionalInterceptors(new RestTemplateClientHttpRequestInterceptor())
                .errorHandler(new RestTemplateErrorHandler())
                .setConnectTimeout(Duration.ofMinutes(3))
                .build();
    }
}