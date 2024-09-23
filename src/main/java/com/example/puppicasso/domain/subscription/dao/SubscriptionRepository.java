package com.example.puppicasso.domain.subscription.dao;

import com.example.puppicasso.domain.subscription.entity.Subscription;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    boolean existsByUserId(@Param("userId") Long userId);

    @Query("SELECT s FROM Subscription s WHERE s.userId = :userId AND s.isActive = true")
    Optional<Subscription> findActiveSubscriptionByUserId(@Param("userId") Long userId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Subscription s " +
            "WHERE s.id = :subscriptionId " +
            "AND (s.expirationDate < CURRENT_DATE OR s.usedGenerateCount >= s.maxGenerateCount)")
    boolean isExpiredOrOverused(@Param("subscriptionId") Long subscriptionId);
}
