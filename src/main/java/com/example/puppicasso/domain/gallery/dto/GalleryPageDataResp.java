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
    private List<String> fileData;

    public GalleryPageDataResp(List<Gallery> galleries) {
        this.fileData = galleries.stream()
                .map(gallery -> Base64.getEncoder().encodeToString(gallery.getData()))
                .collect(Collectors.toList());
    }
}
