package com.example.puppicasso.domain.userInfo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {
    BASIC("GRADE_BASIC", "일반회원"),
    PREMIUM("GRADE_PREMIUM", "프리미엄회원"),
    VIP("GRADE_VIP", "VIP회원");

    private final String key;
    private final String name;
}
