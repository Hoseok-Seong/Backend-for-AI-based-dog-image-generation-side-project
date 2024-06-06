package com.example.puppicasso.domain.ai.prompt;

public enum Atmosphere {
    WARM("capturing a warm and cozy atmosphere with soft, diffused lighting that enhances the dog's fur texture and natural colors."),
    CALM("emphasizing a calm and peaceful mood with muted, soft colors and even lighting that highlights the dog's natural beauty."),
    ENERGETIC("full of energy and excitement with bright, vivid colors and natural lighting that enhances the dog's dynamic pose and vibrant personality."),
    CUTE("highlighting its cuteness with playful props like hats or toys and endearing expressions."),
    PLAYFUL("showcasing its playful nature with interactive elements like toys or vintage decor.");

    private final String prompt;

    Atmosphere(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
