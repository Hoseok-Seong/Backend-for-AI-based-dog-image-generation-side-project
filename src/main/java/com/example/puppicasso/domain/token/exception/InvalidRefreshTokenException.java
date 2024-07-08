package com.example.puppicasso.domain.token.exception;

import com.example.puppicasso.global.error.exception.ErrorCode;
import com.example.puppicasso.global.error.exception.InvalidInputException;

public class InvalidRefreshTokenException extends InvalidInputException {
    public InvalidRefreshTokenException(final String refreshToken) {
        super(refreshToken, ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
