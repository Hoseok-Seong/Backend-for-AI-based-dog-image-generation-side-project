package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.domain.token.dao.RefreshTokenDao;
import com.example.puppicasso.domain.token.dao.RefreshTokenRedisRepository;
import com.example.puppicasso.domain.token.dto.RefreshTokenRedisReq;
import com.example.puppicasso.domain.user.dao.UserFindDao;
import com.example.puppicasso.domain.user.dto.UserSignInReq;
import com.example.puppicasso.domain.user.dto.UserSignInResp;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.user.exception.SignInPasswordMismatchException;
import com.example.puppicasso.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSignInService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserFindDao userFindDao;
    private final RefreshTokenDao refreshTokenDao;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private static final Long REFRESH_TOKEN_EXP = Long.parseLong(System.getenv("JWT_REFRESH_TOKEN_EXP"));

    public UserSignInResp signIn(final String userAgent, final UserSignInReq userSignInReq) {

        final User user = userFindDao.findByUsername(userSignInReq.getUsername());

        if (passwordEncoder.matches(userSignInReq.getPassword(), user.getPassword())) {
            String accessToken = JwtProvider.createAccessToken(user);
            String refreshToken = JwtProvider.createRefreshToken(user);

            refreshTokenDao.deleteRefreshToken(user.getId());

            RefreshTokenRedisReq refreshTokenRedisReq = new RefreshTokenRedisReq(refreshToken, user.getId(), REFRESH_TOKEN_EXP);
            refreshTokenRedisRepository.save(refreshTokenRedisReq.toEntity());

            return new UserSignInResp(user, accessToken, refreshToken);
        }

        throw new SignInPasswordMismatchException(user.getPassword());
    }
}
