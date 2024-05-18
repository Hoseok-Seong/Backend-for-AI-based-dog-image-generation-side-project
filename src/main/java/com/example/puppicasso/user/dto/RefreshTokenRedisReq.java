package com.example.puppicasso.user.dto;

import com.example.puppicasso.user.entity.RefreshTokenRedis;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshTokenRedisReq {
    private String refreshToken;
    private Long id;
    private Long expiredAt;

    public RefreshTokenRedis toEntity() {
        return RefreshTokenRedis.builder()
                .id(id)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();
    }
}
