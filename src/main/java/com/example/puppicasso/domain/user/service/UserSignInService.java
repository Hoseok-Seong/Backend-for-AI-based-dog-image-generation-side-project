package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.domain.token.dao.RefreshTokenDao;
import com.example.puppicasso.domain.token.dao.RefreshTokenRedisRepository;
import com.example.puppicasso.domain.token.dto.RefreshTokenRedisReq;
import com.example.puppicasso.domain.user.dao.UserFindDao;
import com.example.puppicasso.domain.user.dto.UserLoginReq;
import com.example.puppicasso.domain.user.dto.UserLoginResp;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.user.exception.SignInPasswordMismatchException;
import com.example.puppicasso.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> signIn(final String userAgent, final UserLoginReq userLoginReq) {

        final User user = userFindDao.findByUsername(userLoginReq.getUsername());

        if (passwordEncoder.matches(userLoginReq.getPassword(), user.getPassword())) {
            String accessToken = JwtProvider.createAccessToken(user);
            String refreshToken = JwtProvider.createRefreshToken(user);

            refreshTokenDao.deleteRefreshToken(user.getId());

            RefreshTokenRedisReq refreshTokenRedisReq = new RefreshTokenRedisReq(refreshToken, user.getId(), REFRESH_TOKEN_EXP);
            refreshTokenRedisRepository.save(refreshTokenRedisReq.toEntity());

            return ResponseEntity.ok().header(JwtProvider.ACCESS_TOKEN_HEADER, accessToken)
                    .header(JwtProvider.REFRESH_TOKEN_HEADER, refreshToken).body(new UserLoginResp(user));
        }

        throw new SignInPasswordMismatchException(user.getPassword());
    }
}
