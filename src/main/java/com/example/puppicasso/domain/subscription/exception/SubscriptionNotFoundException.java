package com.example.puppicasso.domain.subscription.exception;

import com.example.puppicasso.global.error.exception.DataNotFoundException;

public class SubscriptionNotFoundException extends DataNotFoundException {
    public SubscriptionNotFoundException(Long userId) {
        super("user" + userId + "'s Subscription is not found");
    }
}
