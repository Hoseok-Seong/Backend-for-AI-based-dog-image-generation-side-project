package com.example.puppicasso.domain.subscription.repository;

import com.example.puppicasso.domain.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
