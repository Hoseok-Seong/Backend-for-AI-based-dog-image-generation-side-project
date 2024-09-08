package com.example.puppicasso.domain.subscription.entity;

import com.example.puppicasso.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "subscription")
public class Subscription extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int maxGenerateCount;

    @Column(nullable = false)
    private int usedGenerateCount;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    private LocalDateTime expirationDate;

    @Builder
    public Subscription(Long id, Long userId, Type type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.price = type.getPrice();
        this.maxGenerateCount = type.getMaxGenerateCount();
        this.usedGenerateCount = type.getUsedGenerateCount();
        this.isActive = true;
        this.expirationDate = type.getExpirationDate();
    }

    public void increaseUsedGenerateCount() {
        this.usedGenerateCount++;
    }
}
