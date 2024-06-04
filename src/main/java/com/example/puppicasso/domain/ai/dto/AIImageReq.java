package com.example.puppicasso.domain.ai.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AIImageReq {
    private String model;
    private String prompt;
    private Integer n;
    private String size;

    @Builder
    AIImageReq(String model, String prompt, Integer n, String size) {
        this.model = model;
        this.prompt = prompt;
        this.n = n;
        this.size = size;
    }
}
