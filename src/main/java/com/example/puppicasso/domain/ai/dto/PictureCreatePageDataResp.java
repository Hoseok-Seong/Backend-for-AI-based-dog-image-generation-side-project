package com.example.puppicasso.domain.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PictureCreatePageDataResp {
    private List<Attribute> breeds;
    private List<Attribute> sizes;
    private List<Attribute> coatLengths;
    private List<Attribute> furTextures;
    private List<Attribute> eyeColors;
    private List<Attribute> earShapes;
    private List<Attribute> noseShapes;
    private List<Attribute> faceShapes;
    private List<Attribute> tailShapes;
    private List<Attribute> expressions;
    private List<Attribute> poses;
    private List<Attribute> coatColors;
    private List<Attribute> themes;

    public static class Attribute {
        @JsonProperty("value")
        private String value;
        @JsonProperty("name")
        private String name;

        public Attribute(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }
}
