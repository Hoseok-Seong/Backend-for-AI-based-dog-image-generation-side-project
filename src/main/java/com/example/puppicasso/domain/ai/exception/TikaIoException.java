package com.example.puppicasso.domain.ai.exception;

import com.example.puppicasso.global.error.exception.BusinessException;
import com.example.puppicasso.global.error.exception.ErrorCode;

public class TikaIoException extends BusinessException {
    public TikaIoException() {
        super(ErrorCode.TIKA_IO_EXCEPTION);
    }
}
