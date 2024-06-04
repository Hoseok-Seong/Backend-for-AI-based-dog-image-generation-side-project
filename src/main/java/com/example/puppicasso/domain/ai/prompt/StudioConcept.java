package com.example.puppicasso.domain.ai.prompt;

public enum StudioConcept {
    CLASSIC_STUDIO("A professional portrait of a dog in a classic studio setting with a plain, elegant background."),
    MODERN_STUDIO("A stylish portrait of a dog in a modern studio with sleek, minimalist design elements."),
    NATURAL_BACKGROUND("A beautiful portrait of a dog with a natural outdoor background, featuring lush greenery or a scenic landscape."),
    HOLIDAY_THEME("A festive portrait of a dog in a studio decorated with holiday themes, such as Christmas or Halloween."),
    VINTAGE_STUDIO("A charming portrait of a dog in a vintage studio setting with retro props and sepia tones.");

    private final String prompt;

    StudioConcept(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
