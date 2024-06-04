package com.example.puppicasso.domain.ai.prompt;

public enum PhotoComposition {
    FRONT_VIEW("A front-facing portrait of a dog, capturing its full facial features directly."),
    SIDE_VIEW("A side profile portrait of a dog, highlighting its unique side features."),
    ANGLE_45("A 45-degree angle portrait of a dog, providing a dynamic view of its face and body."),
    CLOSE_UP("A close-up portrait of a dog, focusing on its detailed facial expressions and features."),
    FULL_BODY("A full-body portrait of a dog, showcasing its entire form and posture.");

    private final String prompt;

    PhotoComposition(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
