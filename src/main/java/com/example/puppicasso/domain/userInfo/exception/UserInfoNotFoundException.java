package com.example.puppicasso.domain.userInfo.exception;

import com.example.puppicasso.global.error.exception.DataNotFoundException;

public class UserInfoNotFoundException extends DataNotFoundException {
    public UserInfoNotFoundException(Long userId) {
        super(userId + " 's UserInfo is not found");
    }
}
