package com.example.puppicasso.domain.main.controller;

import com.example.puppicasso.global.security.MyUserDetails;
import com.example.puppicasso.domain.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/api/main")
    public ResponseEntity<?> mainScreen(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return mainService.getMainScreenData(myUserDetails);
    }
}
