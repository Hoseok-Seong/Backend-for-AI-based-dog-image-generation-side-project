package com.example.puppicasso.domain.user.controller;

import com.example.puppicasso.domain.user.dto.UserResetPasswordReq;
import com.example.puppicasso.domain.user.dto.UserSignInResp;
import com.example.puppicasso.domain.user.dto.UserSignUpResp;
import com.example.puppicasso.domain.user.dto.UserResetPasswordResp;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.user.service.UserSignInService;
import com.example.puppicasso.domain.user.service.UserSignUpService;
import com.example.puppicasso.domain.user.service.UserResetPasswordService;
import com.example.puppicasso.global.jwt.JwtProvider;
import com.example.puppicasso.domain.user.dto.UserSignUpReq;
import com.example.puppicasso.domain.user.dto.UserSignInReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserSignInService userSignInService;
    private final UserSignUpService userSignUpService;
    private final UserResetPasswordService userResetPasswordService;

    @PostMapping("/sign-in")
    public ResponseEntity<UserSignInResp> signIn(@RequestHeader("User-Agent") final String userAgent, @RequestBody @Valid final UserSignInReq userSignInReq) {
        final UserSignInResp userSignInResp = userSignInService.signIn(userAgent, userSignInReq);
        return ResponseEntity.ok().header(JwtProvider.ACCESS_TOKEN_HEADER, userSignInResp.getAccessToken())
                .header(JwtProvider.REFRESH_TOKEN_HEADER, userSignInResp.getRefreshToken()).body(userSignInResp);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserSignUpResp> signUp(@RequestHeader("User-Agent") final String userAgent, @RequestBody @Valid final UserSignUpReq userSignUpReq) {
        final User user = userSignUpService.signUp(userAgent, userSignUpReq);
        return ResponseEntity.ok().body(new UserSignUpResp(user));
    }

    @PutMapping ("/password-reset")
    public ResponseEntity<UserResetPasswordResp> resetPassword(@RequestBody @Valid final UserResetPasswordReq userResetPasswordReq) {
        final User user = userResetPasswordService.resetPassword(userResetPasswordReq);
        return ResponseEntity.ok().body(new UserResetPasswordResp(user));
    }
}
