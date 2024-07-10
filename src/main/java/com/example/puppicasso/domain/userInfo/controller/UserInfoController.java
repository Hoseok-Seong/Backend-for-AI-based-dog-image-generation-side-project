package com.example.puppicasso.domain.userInfo.controller;

import com.example.puppicasso.domain.ai.exception.MaxImageSizeExceededException;
import com.example.puppicasso.domain.userInfo.dto.UserInfoUpdateResp;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import com.example.puppicasso.domain.userInfo.service.UserInfoUpdateService;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoUpdateService userInfoUpdateService;

    @PutMapping("/api/user-info")
    public ResponseEntity<UserInfoUpdateResp> updateUserInfo(@AuthenticationPrincipal final MyUserDetails myUserDetails,
                                                               @RequestPart("image") final MultipartFile file) {
        // 파일 크기 검사 (4MB 이하)
        if (file.getSize() > 4 * 1024 * 1024) {
            throw new MaxImageSizeExceededException();
        }

        final UserInfo userInfo = userInfoUpdateService.updateUserInfo(myUserDetails, file);
        return ResponseEntity.ok().body(new UserInfoUpdateResp(userInfo));
    }
}
