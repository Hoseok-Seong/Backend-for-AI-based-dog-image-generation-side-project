package com.example.puppicasso.domain.token.controller;

import com.example.puppicasso.domain.token.dto.NewAccessTokenReq;
import com.example.puppicasso.domain.token.service.AccessTokenGenerateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final AccessTokenGenerateService accessTokenGenerateService;

    @PostMapping("/access-token")
    public ResponseEntity<?> generateNewAccessToken(@RequestHeader("User-Agent") final String userAgent,
                                                    @RequestBody @Valid final NewAccessTokenReq newAccessTokenReq) {
        return accessTokenGenerateService.generateNewAccessToken(userAgent, newAccessTokenReq);
    }
}
