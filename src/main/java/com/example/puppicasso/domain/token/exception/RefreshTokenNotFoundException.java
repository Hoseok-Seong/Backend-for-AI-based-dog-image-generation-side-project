package com.example.puppicasso.domain.token.exception;

import com.example.puppicasso.global.error.exception.DataNotFoundException;

public class RefreshTokenNotFoundException extends DataNotFoundException {
    public RefreshTokenNotFoundException(Long userId) {
        super(userId + " is not found");
    }
}
