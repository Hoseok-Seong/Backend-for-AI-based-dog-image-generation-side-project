package com.example.puppicasso.domain.token.controller;

import com.example.puppicasso.domain.token.dto.NewAccessTokenReq;
import com.example.puppicasso.domain.token.dto.NewAccessTokenResp;
import com.example.puppicasso.domain.token.service.AccessTokenGenerateService;
import com.example.puppicasso.global.jwt.JwtProvider;
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
    public ResponseEntity<NewAccessTokenResp> generateNewAccessToken(@RequestHeader("User-Agent") final String userAgent,
                                                    @RequestBody @Valid final NewAccessTokenReq newAccessTokenReq) {
        final NewAccessTokenResp newAccessTokenResp = accessTokenGenerateService.generateNewAccessToken(userAgent, newAccessTokenReq);
        return ResponseEntity.ok().header(JwtProvider.ACCESS_TOKEN_HEADER, newAccessTokenResp.getAccessToken())
                .header(JwtProvider.REFRESH_TOKEN_HEADER, newAccessTokenResp.getRefreshToken()).body(newAccessTokenResp);
    }
}
