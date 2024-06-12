package com.example.puppicasso.domain.ai.prompt;

public enum DogAttributes {
    // Breed
    BREED_JINDO("Jindo"),
    BREED_GOLDEN_RETRIEVER("Golden Retriever"),
    BREED_BULLDOG("Bulldog"),
    BREED_POODLE("Poodle"),

    // Size Description
    SIZE_SMALL("Small"),
    SIZE_MEDIUM("Medium"),
    SIZE_LARGE("Large"),

    // Coat Length
    COAT_SHORT("Short-length"),
    COAT_MEDIUM("Medium-length"),
    COAT_LONG("Long-length"),

    // Coat Color
    COAT_WHITE("White"),
    COAT_BLACK("Black"),
    COAT_BROWN("Brown"),
    COAT_GOLDEN("Golden"),

    // Fur Texture
    FUR_SMOOTH("Smooth"),
    FUR_STRAIGHT("Straight"),
    FUR_WAVY("Wavy"),
    FUR_CURLY("Curly"),

    // Eye Color
    EYE_BROWN("Brown"),
    EYE_BLUE("Blue"),
    EYE_GREEN("Green"),
    EYE_AMBER("Amber"),

    // Ear Shape
    EAR_TRIANGULAR("Triangular"),
    EAR_ROUND("Round"),
    EAR_DROOPY("Droopy"),
    EAR_POINTED("Pointed"),

    // Nose Shape
    NOSE_LONG("Long"),
    NOSE_SHORT("Short"),
    NOSE_FLAT("Flat"),

    // Face Shape
    FACE_LONG("Long"),
    FACE_ROUND("Round"),
    FACE_SQUARE("Square"),

    // Tail Shape
    TAIL_CURLED("Curled"),
    TAIL_STRAIGHT("Straight"),
    TAIL_BUSHY("Bushy"),

    // Expression
    EXPRESSION_HAPPY("Happy"),
    EXPRESSION_SAD("Sad"),
    EXPRESSION_ALERT("Alert"),
    EXPRESSION_RELAXED("Relaxed"),

    // Pose
    POSE_SITTING("Sitting"),
    POSE_STANDING("Standing"),
    POSE_RUNNING("Running"),
    POSE_LYING_DOWN("Lying Down");

    private final String description;

    DogAttributes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

