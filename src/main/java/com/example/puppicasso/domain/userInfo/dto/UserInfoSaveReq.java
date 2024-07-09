package com.example.puppicasso.domain.userInfo.dto;

import com.example.puppicasso.domain.userInfo.entity.Grade;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoSaveReq {
    private Long userId;
    private Grade grade;

    public UserInfoSaveReq(final Long userId, final Grade grade) {
        this.userId = userId;
        this.grade = grade;
    }

    public UserInfo toEntity() {
        return UserInfo.builder()
                .userId(userId)
                .profilePic(null)
                .grade(grade)
                .build();
    }
}
