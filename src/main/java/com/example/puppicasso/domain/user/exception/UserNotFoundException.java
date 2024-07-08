package com.example.puppicasso.domain.user.exception;

import com.example.puppicasso.global.error.exception.DataNotFoundException;

public class UserNotFoundException extends DataNotFoundException {
    public UserNotFoundException(Long userId) {
        super(userId + " is not found");
    }
}
