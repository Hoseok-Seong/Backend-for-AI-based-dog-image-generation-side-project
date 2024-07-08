package com.example.puppicasso.domain.user.controller;

import com.example.puppicasso.domain.user.service.UserSignInService;
import com.example.puppicasso.domain.user.service.UserSignUpService;
import com.example.puppicasso.domain.user.service.UserUpdateProfileService;
import com.example.puppicasso.global.security.MyUserDetails;
import com.example.puppicasso.domain.user.dto.UserJoinReq;
import com.example.puppicasso.domain.user.dto.UserLoginReq;
import com.example.puppicasso.domain.user.dto.UserUpdateReq;
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
    public ResponseEntity<?> signIn(@RequestHeader("User-Agent") final String userAgent, @RequestBody @Valid final UserLoginReq userLoginReq) {
        return userSignInService.signIn(userAgent, userLoginReq);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestHeader("User-Agent") final String userAgent, @RequestBody @Valid final UserJoinReq userJoinReq) {
        return userSignUpService.signUp(userAgent, userJoinReq);
    }

    @PutMapping ("/api/users/profile-picture")
    public ResponseEntity<?> update(@AuthenticationPrincipal final MyUserDetails myUserDetails,
                                    @RequestBody @Valid final UserUpdateReq userUpdateReq) {
        return userUpdateProfileService.updateProfile(myUserDetails, userUpdateReq);
    }
}
