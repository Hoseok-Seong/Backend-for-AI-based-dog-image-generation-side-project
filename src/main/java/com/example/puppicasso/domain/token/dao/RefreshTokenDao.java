package com.example.puppicasso.domain.token.dao;

import com.example.puppicasso.domain.token.entity.RefreshTokenRedis;
import com.example.puppicasso.domain.token.exception.InvalidRefreshTokenException;
import com.example.puppicasso.domain.token.exception.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenDao {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    public RefreshTokenRedis findById(final Long userId) {
        return refreshTokenRedisRepository.findById(userId).orElse(null);
    }

    public void validateRefreshToken(final Long userId, final String refreshToken) {
        if (!refreshTokenRedisRepository.existsById(userId)) {
            throw new RefreshTokenNotFoundException(userId);
        }

        if (!findById(userId).getRefreshToken().equals(refreshToken)) {
            refreshTokenRedisRepository.delete(findById(userId));
            throw new InvalidRefreshTokenException(refreshToken);
        }
    }

    public void deleteRefreshToken(final Long userId) {
        RefreshTokenRedis refreshToken = findById(userId);

        if (refreshToken != null) {
            refreshTokenRedisRepository.delete(refreshToken);
        }
    }
}
