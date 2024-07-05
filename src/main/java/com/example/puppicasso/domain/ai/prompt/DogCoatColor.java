package com.example.puppicasso.domain.ai.prompt;

public enum DogCoatColor {
    WHITE("White", "흰색"),
    BLACK("Black", "검은색"),
    BROWN("Brown", "갈색"),
    GOLDEN("Golden", "금색"),
    CREAM("Cream", "크림색"),
    GRAY("Gray", "회색"),
    RED("Red", "붉은색"),
    BLUE("Blue", "푸른색"),
    SILVER("Silver", "은색"),
    TAN("Tan", "황갈색");

    private final String englishDescription;
    private final String koreanDescription;

    DogCoatColor(String englishDescription, String koreanDescription) {
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
