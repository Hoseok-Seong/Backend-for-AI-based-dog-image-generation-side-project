package com.example.puppicasso.domain.main.controller;

import com.example.puppicasso.global.security.MyUserDetails;
import com.example.puppicasso.domain.user.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/api/main")
    public ResponseEntity<?> mainScreen(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return mainService.getMainScreenData(myUserDetails);
    }
}
