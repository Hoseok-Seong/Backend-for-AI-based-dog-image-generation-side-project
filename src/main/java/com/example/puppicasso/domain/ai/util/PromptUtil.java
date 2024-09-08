package com.example.puppicasso.domain.ai.util;

import com.example.puppicasso.domain.ai.dto.AIImageReq;

public class PromptUtil {
    public static String generateModelsLabPromptAllParams(AIImageReq details) {
        return "Change the background of the dog in this photo to look like it was taken in a modern studio. The dog is a "
                + details.getSizeDesc() + " " + details.getBreed() + " with a "
                + details.getCoatLength() + ", " + details.getCoatColor() + " coat. Its fur is "
                + details.getFurTexture() + ", and it has " + details.getEyeColor() + " eyes. The dog's ears are "
                + details.getEarShape() + ", and it has a " + details.getNoseShape() + " nose and a "
                + details.getFaceShape() + " face. Its tail is " + details.getTailShape() + ". The dog appears "
                + details.getExpression() + " and is " + details.getPose() + ".";
    }

    public static String generateModelsLabPromptSimpleVersion(AIImageReq details) {
        return "Change the background of the dog in this photo to look like it was taken in a modern studio. The dog is a "
                + details.getSizeDesc() + " " + details.getBreed() +  ". The dog appears " + details.getExpression()
                + ". Ensure the generated image closely resembles the original dog in the photo, preserving over 90% of its unique features such as fur color, markings, and overall appearance.";
    }

    public static String generateModelsLabPromptThemeSimpleVersion(AIImageReq details) {
        String theme = details.getTheme().toLowerCase();

        return switch (theme) {
            case "cartoon" -> "Turn the dog in this photo into a cartoon version.";
            case "sci-fi" -> "Transform the dog into a futuristic sci-fi version.";
            case "fantasy" -> "Turn the dog into a magical fantasy creature.";
            case "cyberpunk" -> "Create a cyberpunk version of the dog.";
            case "steampunk" -> "Reimagine the dog in a steampunk universe.";
            case "horror" -> "Create a horror-themed version of the dog.";
            case "vintage" -> "Make the dog look like it belongs in a vintage 1920s photo.";
            case "anime" -> "Turn the dog into an anime character.";
            case "pixel art" -> "Convert the dog into a pixel art version.";
            case "watercolor" -> "Paint the dog in a watercolor style.";
            default -> "Change the background of the dog in this photo to look like it was taken in a modern studio.";
        };
    }

    public static String generateModelsLabPromptTheme(AIImageReq details) {
        String theme = details.getTheme().toLowerCase();

        return switch (theme) {
            case "cartoon" -> "Turn the dog in this photo into a cartoon version. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Ensure the dog retains 90% of its original features, such as fur color, markings, and unique appearance.";
            case "sci-fi" -> "Transform the dog into a futuristic sci-fi version. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Place the dog in a science-fiction setting while preserving 90% of its original features.";
            case "fantasy" -> "Turn the dog into a magical fantasy creature. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Preserve over 90% of the dog's features while incorporating elements of a fantasy world.";
            case "cyberpunk" -> "Create a cyberpunk version of the dog. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Place the dog in a cyberpunk city while retaining 90% of its original appearance.";
            case "steampunk" -> "Reimagine the dog in a steampunk universe. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Ensure the dog retains 90% of its original traits with steampunk elements.";
            case "horror" -> "Create a horror-themed version of the dog. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Add eerie details while keeping 90% of the original dog's appearance.";
            case "vintage" -> "Make the dog look like it belongs in a vintage 1920s photo. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Retain 90% of the dog's features while giving it a classic, old-fashioned look.";
            case "anime" -> "Turn the dog into an anime character. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Ensure the dog keeps 90% of its original features while adopting an anime art style.";
            case "pixel art" -> "Convert the dog into a pixel art version. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Keep 90% of its original details while turning it into a pixelated version.";
            case "watercolor" -> "Paint the dog in a watercolor style. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + " with a "
                    + details.getExpression() + " expression. Retain 90% of its original traits while using soft, watercolor techniques.";
            default -> "Change the background of the dog in this photo to look like it was taken in a modern studio. The dog is a "
                    + details.getSizeDesc() + " " + details.getBreed() + ". The dog appears "
                    + details.getExpression() + ". Ensure the generated image closely resembles the original dog in the photo, preserving over 90% of its unique features.";
        };
    }
}

