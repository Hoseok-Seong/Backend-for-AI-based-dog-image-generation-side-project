package com.example.puppicasso.domain.subscription.repository;

import com.example.puppicasso.domain.subscription.entity.Subscription;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT s FROM Subscription s WHERE s.userId = :userId")
    List<Subscription> findByUserId(@Param("userId") Long userId);

    @Query("SELECT s FROM Subscription s WHERE s.userId = :userId ORDER BY s.price DESC")
    List<Subscription> findTopByUserIdOrderByPriceDesc(@Param("userId") Long userId, Pageable pageable);

    default Subscription findTopByUserIdOrderByPriceDesc(Long userId) {
        Pageable pageable = PageRequest.of(0, 1);
        List<Subscription> result = findTopByUserIdOrderByPriceDesc(userId, pageable);
        return result.isEmpty() ? null : result.get(0);
    }
}
