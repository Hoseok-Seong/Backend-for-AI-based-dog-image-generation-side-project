package com.example.puppicasso.domain.user.exception;

import com.example.puppicasso.global.error.exception.ErrorCode;
import com.example.puppicasso.global.error.exception.InvalidInputException;

public class SignInPasswordMismatchException extends InvalidInputException {
    public SignInPasswordMismatchException(final String password) {
        super(password, ErrorCode.SIGNIN_PASSWORD_MISMATCH);
    }
}
