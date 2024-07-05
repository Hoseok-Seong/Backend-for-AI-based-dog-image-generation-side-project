package com.example.puppicasso.global.error.exception;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException(String value) {
        super(value, ErrorCode.INVALID_TOKEN);
    }

    public InvalidTokenException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
