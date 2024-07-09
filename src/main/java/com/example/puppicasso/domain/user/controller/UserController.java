package com.example.puppicasso.domain.user.controller;

import com.example.puppicasso.domain.user.dto.UserSignInResp;
import com.example.puppicasso.domain.user.dto.UserSignUpResp;
import com.example.puppicasso.domain.user.dto.UserUpdateProfileResp;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.user.service.UserSignInService;
import com.example.puppicasso.domain.user.service.UserSignUpService;
import com.example.puppicasso.domain.user.service.UserUpdateProfileService;
import com.example.puppicasso.global.jwt.JwtProvider;
import com.example.puppicasso.global.security.MyUserDetails;
import com.example.puppicasso.domain.user.dto.UserSignUpReq;
import com.example.puppicasso.domain.user.dto.UserSignInReq;
import com.example.puppicasso.domain.user.dto.UserUpdateProfileReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final UserUpdateProfileService userUpdateProfileService;

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

    @PutMapping ("/api/users/profile")
    public ResponseEntity<UserUpdateProfileResp> updateProfile(@AuthenticationPrincipal final MyUserDetails myUserDetails,
                                    @RequestBody @Valid final UserUpdateProfileReq userUpdateProfileReq) {
        final User user = userUpdateProfileService.updateProfile(myUserDetails, userUpdateProfileReq);
        return ResponseEntity.ok().body(new UserUpdateProfileResp(user));
    }
}
