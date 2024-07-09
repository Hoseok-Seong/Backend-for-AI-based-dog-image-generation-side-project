package com.example.puppicasso.domain.ai.util;

import com.example.puppicasso.domain.ai.dto.AIImageReq;

public class PromptUtil {
    public static String generateModelsLabPrompt(AIImageReq details) {
        return "Change the background of the dog in this photo to look like it was taken in a modern studio. The dog is a "
                + details.getSizeDesc() + " " + details.getBreed() + " with a "
                + details.getCoatLength() + ", " + details.getCoatColor() + " coat. Its fur is "
                + details.getFurTexture() + ", and it has " + details.getEyeColor() + " eyes. The dog's ears are "
                + details.getEarShape() + ", and it has a " + details.getNoseShape() + " nose and a "
                + details.getFaceShape() + " face. Its tail is " + details.getTailShape() + ". The dog appears "
                + details.getExpression() + " and is " + details.getPose() + ".";
    }
}

