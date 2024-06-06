package com.example.puppicasso.domain.ai.prompt;

public enum StudioConcept {
    CLASSIC_STUDIO("a classic studio with a plain, elegant background, simple and sophisticated, with cool tones that create a tranquil and serene environment."),
    MODERN_STUDIO("a modern studio with sleek, minimalist design elements, clean and uncluttered, with warm tones that create a welcoming and comfortable environment."),
    NATURAL_BACKGROUND("an outdoor background with lush greenery or a scenic landscape, lively and colorful, featuring elements like trees, flowers, or open fields that create an energetic and joyful environment."),
    HOLIDAY_THEME("a studio decorated with holiday themes, such as Christmas or Halloween, festive and cheerful, featuring holiday decorations like ornaments, lights, or themed props that create a playful and joyful environment."),
    VINTAGE_STUDIO("a vintage studio with retro props and sepia tones, charming and nostalgic, featuring retro elements like old-fashioned furniture, toys, or decorations that create a playful and fun environment.");

    private final String prompt;

    StudioConcept(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
