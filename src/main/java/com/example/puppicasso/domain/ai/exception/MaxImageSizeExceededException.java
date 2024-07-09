package com.example.puppicasso.domain.ai.exception;

import com.example.puppicasso.global.error.exception.BusinessException;
import com.example.puppicasso.global.error.exception.ErrorCode;

public class MaxImageSizeExceededException extends BusinessException {

    public MaxImageSizeExceededException() {
        super(ErrorCode.MAX_IMAGE_SIZE_EXCEEDED);
    }
}
