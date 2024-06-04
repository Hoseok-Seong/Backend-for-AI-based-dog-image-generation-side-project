package com.example.puppicasso.domain.ai.prompt;

public enum Atmosphere {
    WARM("A heartwarming portrait of a dog, capturing a warm and cozy atmosphere with soft lighting."),
    CALM("A serene portrait of a dog, emphasizing a calm and peaceful mood with muted colors."),
    ENERGETIC("A vibrant portrait of a dog, full of energy and excitement, with bright colors and dynamic poses."),
    CUTE("An adorable portrait of a dog, highlighting its cuteness with playful props and endearing expressions."),
    PLAYFUL("A playful portrait of a dog, showcasing its mischievous and fun-loving nature with interactive elements.");

    private final String prompt;

    Atmosphere(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
