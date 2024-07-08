package com.example.puppicasso.domain.token.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash("refreshToken")
public class RefreshTokenRedis {
    @Id
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @TimeToLive
    private Long expiredAt;

    @Builder
    public RefreshTokenRedis(Long id, String refreshToken, Long expiredAt) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.expiredAt = expiredAt;
    }

    public void updateRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
