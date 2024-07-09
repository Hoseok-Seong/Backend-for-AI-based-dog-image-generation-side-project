package com.example.puppicasso.domain.gallery.controller;

import com.example.puppicasso.domain.gallery.dto.GalleryPageDataResp;
import com.example.puppicasso.domain.gallery.service.GalleryPageDataService;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryPageDataService galleryPageDataService;

    @GetMapping("/api/gallery")
    public ResponseEntity<GalleryPageDataResp> getGalleryPageData(@AuthenticationPrincipal final MyUserDetails myUserDetails) {
        final GalleryPageDataResp galleryPageDataResp = galleryPageDataService.getGalleryPageData(myUserDetails);
        return ResponseEntity.ok().body(galleryPageDataResp);
    }
}
