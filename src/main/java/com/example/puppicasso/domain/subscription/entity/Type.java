package com.example.puppicasso.domain.subscription.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public enum Type {
    WELCOME("WELCOME_COUPON", "첫 가입 웰컴쿠폰", 0, 10, 0, LocalDateTime.now().plusYears(1));

    private final String key;
    private final String name;
    private final int price;
    private final int maxGenerateCount;
    private final int usedGenerateCount;
    private final LocalDateTime expirationDate;
}
