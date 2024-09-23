package com.example.puppicasso.domain.subscription.dao;

import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.domain.subscription.exception.SubscriptionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionDao {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription findActiveSubscription(final Long userId) {
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findActiveSubscriptionByUserId(userId);

        if(subscriptionOpt.isEmpty()) {
            throw new SubscriptionNotFoundException(userId);
        } else {
            return subscriptionOpt.get();
        }
    }

    public boolean validateSubscription(final Long userId) {
        Subscription subscription = findActiveSubscription(userId);

        return !subscriptionRepository.isExpiredOrOverused(subscription.getId());
    }
}
