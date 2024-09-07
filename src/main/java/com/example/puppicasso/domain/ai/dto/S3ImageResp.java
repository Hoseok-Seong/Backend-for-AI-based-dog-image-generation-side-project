package com.example.puppicasso.domain.ai.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class S3ImageResp {
    private String url;
    private String key;

    @Builder
    public S3ImageResp(String url, String key) {
        this.url = url;
        this.key = key;
    }
}
