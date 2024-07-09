package com.example.puppicasso.domain.subscription.dto;

import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.domain.subscription.entity.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscriptionSaveReq {

    private Long userId;
    private Type type;

    public SubscriptionSaveReq(final Long userId, final Type type) {
        this.userId = userId;
        this.type = type;
    }

    public Subscription toEntity() {
        return Subscription.builder()
                .userId(userId)
                .type(type)
                .build();
    }
}
