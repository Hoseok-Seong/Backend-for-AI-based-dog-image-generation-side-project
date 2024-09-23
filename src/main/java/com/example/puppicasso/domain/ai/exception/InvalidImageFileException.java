package com.example.puppicasso.domain.ai.exception;

import com.example.puppicasso.global.error.exception.BusinessException;
import com.example.puppicasso.global.error.exception.ErrorCode;

public class InvalidImageFileException extends BusinessException {
    public InvalidImageFileException() {
        super(ErrorCode.INVALID_IMAGE_FILE_EXCEPTION);
    }
}
