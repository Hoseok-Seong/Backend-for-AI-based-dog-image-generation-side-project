package com.example.puppicasso.domain.userInfo.dto;

import com.example.puppicasso.domain.userInfo.entity.Grade;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoUpdateResp {
    private Long id;
    private Long userId;
    private byte[] profilePic;
    private Grade grade;

    public UserInfoUpdateResp(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.userId = userInfo.getUserId();
        this.profilePic = userInfo.getProfilePic();
        this.grade = userInfo.getGrade();
    }
}
