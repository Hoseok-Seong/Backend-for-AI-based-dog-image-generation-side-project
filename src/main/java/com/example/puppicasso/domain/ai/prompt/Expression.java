package com.example.puppicasso.domain.ai.prompt;

public enum Expression {
    EXPRESSION_HAPPY("Happy", "행복한"),
    EXPRESSION_CURIOUS("Curious", "호기심 많은"),
    EXPRESSION_PLAYFUL("Playful", "장난스러운"),
    EXPRESSION_CHILL("Chill", "여유로운"),
    EXPRESSION_RELAXED("Relaxed", "편안한"),
    EXPRESSION_EXCITED("Excited", "신난"),
    EXPRESSION_SURPRISED("Surprised", "놀란"),
    EXPRESSION_SCARED("Scared", "무서워하는"),
    EXPRESSION_BORED("Bored", "지루한"),
    EXPRESSION_SAD("Sad", "슬픈"),
    EXPRESSION_TIRED("Tired", "피곤한"),
    EXPRESSION_ANGRY("Angry", "화난"),
    EXPRESSION_ALERT("Alert", "경계하는");

    private final String englishDescription;
    private final String koreanDescription;

    Expression(String englishDescription, String koreanDescription) {
        this.englishDescription = englishDescription;
        this.koreanDescription = koreanDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public String getKoreanDescription() {
        return koreanDescription;
    }
}
