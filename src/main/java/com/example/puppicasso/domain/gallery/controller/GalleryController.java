package com.example.puppicasso.domain.gallery.controller;

import com.example.puppicasso.domain.gallery.service.GalleryService;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping("/api/galleries")
    public ResponseEntity<?> galleriesScreen(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return galleryService.getGalleriesScreenData(myUserDetails);
    }
}
