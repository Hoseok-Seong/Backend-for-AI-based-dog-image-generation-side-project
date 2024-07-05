package com.example.puppicasso.domain.ai.dto;

import com.example.puppicasso.domain.ai.prompt.DogAttributes;
import com.example.puppicasso.domain.ai.prompt.DogBreed;
import com.example.puppicasso.domain.ai.prompt.DogCoatColor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AIImageReq {
    // 강아지 특성
    private String breed;
    private String sizeDesc;
    private String coatLength;
    private String coatColor;
    private String furTexture;
    private String eyeColor;
    private String earShape;
    private String noseShape;
    private String faceShape;
    private String tailShape;
    private String expression;
    private String pose;

    @Builder
    AIImageReq(String breed, String sizeDesc, String coatLength, String coatColor, String furTexture, String eyeColor, String earShape, String noseShape, String faceShape, String tailShape, String expression, String pose) {
        this.breed = breed;
        this.sizeDesc = sizeDesc;
        this.coatLength = coatLength;
        this.coatColor = coatColor;
        this.furTexture = furTexture;
        this.eyeColor = eyeColor;
        this.earShape = earShape;
        this.noseShape = noseShape;
        this.faceShape = faceShape;
        this.tailShape = tailShape;
        this.expression = expression;
        this.pose = pose;
    }
}
