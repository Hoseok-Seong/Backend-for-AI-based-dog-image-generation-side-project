package com.example.puppicasso.user.controller;

import com.example.puppicasso.global.security.MyUserDetails;
import com.example.puppicasso.user.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GalleryController {

    private final MainService mainService;

    @PostMapping("/main")
    public ResponseEntity<?> mainScreen(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return ResponseEntity.ok(mainService.getMainScreenData(myUserDetails));
    }
}
