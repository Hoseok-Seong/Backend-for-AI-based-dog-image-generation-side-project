package com.example.puppicasso.domain.gallery.dto;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GalleryPageDataResp {
    private List<String> imageUrls;

    public GalleryPageDataResp(List<Gallery> galleries) {
        this.imageUrls = galleries.stream()
                .map(Gallery::getImageUrl)
                .collect(Collectors.toList());
    }
}
