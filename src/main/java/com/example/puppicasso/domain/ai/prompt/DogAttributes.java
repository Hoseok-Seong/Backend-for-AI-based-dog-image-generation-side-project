package com.example.puppicasso.domain.ai.prompt;

public enum DogAttributes {

    // Size Description
    SIZE_SMALL("Small", "소형"),
    SIZE_MEDIUM("Medium", "중형"),
    SIZE_LARGE("Large", "대형"),

    // Coat Length
    COAT_SHORT("Short-length", "짧은 털"),
    COAT_MEDIUM("Medium-length", "중간 털"),
    COAT_LONG("Long-length", "긴 털"),

    // Fur Texture
    FUR_SMOOTH("Smooth", "매끄러운"),
    FUR_STRAIGHT("Straight", "곧은"),
    FUR_WAVY("Wavy", "물결 모양"),
    FUR_CURLY("Curly", "곱슬곱슬한"),

    // Eye Color
    EYE_BROWN("Brown", "갈색"),
    EYE_BLUE("Blue", "파란색"),
    EYE_GREEN("Green", "녹색"),
    EYE_AMBER("Amber", "호박색"),

    // Ear Shape
    EAR_TRIANGULAR("Triangular", "삼각형"),
    EAR_ROUND("Round", "둥근"),
    EAR_DROOPY("Droopy", "처진"),
    EAR_POINTED("Pointed", "뾰족한"),

    // Nose Shape
    NOSE_LONG("Long", "긴"),
    NOSE_SHORT("Short", "짧은"),
    NOSE_FLAT("Flat", "평평한"),

    // Face Shape
    FACE_LONG("Long", "긴"),
    FACE_ROUND("Round", "둥근"),
    FACE_SQUARE("Square", "네모난"),

    // Tail Shape
    TAIL_CURLED("Curled", "말린"),
    TAIL_STRAIGHT("Straight", "곧은"),
    TAIL_BUSHY("Bushy", "털이 많은"),

    // Expression
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
    EXPRESSION_ALERT("Alert", "경계하는"),

    // Pose
    POSE_SITTING("Sitting", "앉아 있는"),
    POSE_STANDING("Standing", "서 있는"),
    POSE_RUNNING("Running", "달리는"),
    POSE_LYING_DOWN("Lying Down", "누워 있는"),

    // UnKnown
    UNKNOWN("Unknown", "잘 모르겠음");

    private final String englishDescription;
    private final String koreanDescription;

    DogAttributes(String englishDescription, String koreanDescription) {
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
