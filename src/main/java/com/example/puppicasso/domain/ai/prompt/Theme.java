package com.example.puppicasso.domain.ai.prompt;

public enum Theme {
    THEME_MODERN_STUDIO("", "스튜디오"),
    THEME_CARTOON("Cartoon", "카툰"),
    THEME_ANIME("Anime", "애니메이션"),
    THEME_PIXEL_ART("Pixel Art", "픽셀 아트"),
    THEME_WATERCOLOR("Watercolor", "수채화"),
    THEME_SCI_FI("Sci-Fi", "공상과학"),
    THEME_FANTASY("Fantasy", "판타지"),
    THEME_CYBERPUNK("Cyberpunk", "사이버펑크"),
    THEME_STEAMPUNK("Steampunk", "스팀펑크"),
    THEME_HORROR("Horror", "호러"),
    THEME_VINTAGE("Vintage", "빈티지"),
    ;

    private final String englishDescription;
    private final String koreanDescription;

    Theme(String englishDescription, String koreanDescription) {
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
