package com.example.puppicasso.domain.ai.exception;

import com.example.puppicasso.global.error.exception.BusinessException;
import com.example.puppicasso.global.error.exception.ErrorCode;

public class S3IoException extends BusinessException {
    public S3IoException() {
        super(ErrorCode.S3_IO_EXCEPTION);
    }
}
