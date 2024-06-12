package com.example.puppicasso.domain.ai.dto;

import com.example.puppicasso.domain.ai.prompt.DogAttributes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AIImageReq {
    // 강아지 특성
    private DogAttributes breed;
    private DogAttributes sizeDesc;
    private DogAttributes coatLength;
    private DogAttributes coatColor;
    private DogAttributes furTexture;
    private DogAttributes eyeColor;
    private DogAttributes earShape;
    private DogAttributes noseShape;
    private DogAttributes faceShape;
    private DogAttributes tailShape;
    private DogAttributes expression;
    private DogAttributes pose;

    @Builder
    AIImageReq(DogAttributes breed, DogAttributes sizeDesc, DogAttributes coatLength, DogAttributes coatColor, DogAttributes furTexture, DogAttributes eyeColor, DogAttributes earShape, DogAttributes noseShape, DogAttributes faceShape, DogAttributes tailShape, DogAttributes expression, DogAttributes pose) {
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
