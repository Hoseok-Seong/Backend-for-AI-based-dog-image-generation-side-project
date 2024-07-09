package com.example.puppicasso.domain.main.controller;

import com.example.puppicasso.domain.main.dto.MainPageDataResp;
import com.example.puppicasso.domain.main.service.MainPageDataService;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainPageDataService mainPageDataService;

    @GetMapping("/api/main")
    public ResponseEntity<MainPageDataResp> getMainPageData(@AuthenticationPrincipal final MyUserDetails myUserDetails) {
        final MainPageDataResp mainPageDataResp = mainPageDataService.getMainPageData(myUserDetails);
        return ResponseEntity.ok().body(mainPageDataResp);
    }
}
