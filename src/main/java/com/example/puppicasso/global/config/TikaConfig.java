package com.example.puppicasso.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.tika.Tika;

@Configuration
public class TikaConfig {
    @Bean
    public Tika tika() {
        return new Tika();
    }
}
