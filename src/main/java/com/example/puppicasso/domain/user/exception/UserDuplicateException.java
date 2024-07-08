package com.example.puppicasso.domain.user.exception;

import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.global.error.exception.ErrorCode;
import com.example.puppicasso.global.error.exception.InvalidInputException;

public class UserDuplicateException extends InvalidInputException {
    public UserDuplicateException(final User user) {
        super(user.getUsername(), ErrorCode.USER_DUPLICATION);
    }
}
