package com.example.puppicasso.domain.subscription.exception;

import com.example.puppicasso.global.error.exception.BusinessException;
import com.example.puppicasso.global.error.exception.ErrorCode;

public class InvalidSubscriptionException extends BusinessException {
    public InvalidSubscriptionException() {
        super(ErrorCode.INVALID_SUBSCRIPTION);
    }
}
