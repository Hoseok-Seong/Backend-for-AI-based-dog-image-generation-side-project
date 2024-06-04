package com.example.puppicasso.domain.ai.controller;

import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AIController {

    /**
     * @author : hsseong
     * @apiNote  : ChatGPT에서 사용하는 환경 구성
     * @since : 2024/06/04
     */
    @GetMapping("/api/main")
    public ResponseEntity<?> mainScreen(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return ResponseEntity.ok().body("AI Controller");
    }
}
