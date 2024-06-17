package com.example.puppicasso.domain.ai.dto;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.entity.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AIImageResp {
    private Long id;
    private Long userId;
    private String name;
    private Type type;
    private String imageUrl;

    public AIImageResp(Gallery gallery, String imageUrl) {
        this.id = gallery.getId();
        this.userId = gallery.getUserId();
        this.name = gallery.getName();
        this.type = gallery.getType();
        this.imageUrl = imageUrl;
    }
}
