package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.repository.GalleryRepository;
import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.domain.subscription.entity.Type;
import com.example.puppicasso.domain.subscription.repository.SubscriptionRepository;
import com.example.puppicasso.domain.user.repository.RefreshTokenRedisRepository;
import com.example.puppicasso.domain.user.repository.UserRepository;
import com.example.puppicasso.domain.userInfo.entity.Grade;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import com.example.puppicasso.domain.userInfo.repository.UserInfoRepository;
import com.example.puppicasso.global.jwt.JwtProvider;
import com.example.puppicasso.global.security.MyUserDetails;
import com.example.puppicasso.domain.user.dto.RefreshTokenRedisReq;
import com.example.puppicasso.domain.user.dto.UserJoinReq;
import com.example.puppicasso.domain.user.dto.UserJoinResp;
import com.example.puppicasso.domain.user.dto.UserLoginReq;
import com.example.puppicasso.domain.user.dto.UserLoginResp;
import com.example.puppicasso.domain.user.dto.UserUpdateReq;
import com.example.puppicasso.domain.user.dto.UserUpdateResp;
import com.example.puppicasso.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final UserInfoRepository userInfoRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final GalleryRepository galleryRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Long REFRESH_TOKEN_EXP = Long.parseLong(System.getenv("JWT_REFRESH_TOKEN_EXP"));

    @Transactional
    public ResponseEntity<?> login(String userAgent, UserLoginReq userLoginReq) {
        User user = userRepository
                .findByUsername(userLoginReq.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));

        if (passwordEncoder.matches(userLoginReq.getPassword(), user.getPassword())) {
            String accessToken = JwtProvider.createAccessToken(user);
            String refreshToken = JwtProvider.createRefreshToken(user);

            // RefreshToken Redis DB 삭제
            refreshTokenRedisRepository.findById(user.getId())
                    .ifPresent(refreshTokenRedisRepository::delete);

            // RefreshToken Redis DB 저장
            RefreshTokenRedisReq refreshTokenRedisReq = new RefreshTokenRedisReq(refreshToken, user.getId(), REFRESH_TOKEN_EXP);
            refreshTokenRedisRepository.save(refreshTokenRedisReq.toEntity());

            return ResponseEntity.ok().header(JwtProvider.ACCESS_TOKEN_HEADER, accessToken)
                    .header(JwtProvider.REFRESH_TOKEN_HEADER, refreshToken).body(new UserLoginResp(user));
        }

        throw new RuntimeException("로그인 로직 실패");
    }

    @Transactional
    public ResponseEntity<?> join(String userAgent, UserJoinReq userJoinReq) {
        String rawPassword = userJoinReq.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
        userJoinReq.setPassword(encPassword);

        Optional<User> userOptional = userRepository.findByUsername(userJoinReq.getUsername());

        if (userOptional.isPresent()) {
            throw new DataIntegrityViolationException("사용자가 이미 존재합니다");
        }

        try {
            User user = userRepository.save(userJoinReq.toEntity());

            // Welcome 쿠폰(Subscription) 생성
            Subscription welcomeSubscription = Subscription.builder()
                    .type(Type.WELCOME)
                    .build();

            Subscription subscription = subscriptionRepository.save(welcomeSubscription);

            // UserInfo 생성
            UserInfo userInfo = UserInfo.builder()
                    .userId(user.getId())
                    .profilePic(null)
                    .grade(Grade.BASIC)
                    .subscriptionId(subscription.getId())
                    .build();

            userInfoRepository.save(userInfo);

            return ResponseEntity.ok().body(new UserJoinResp(user));
        } catch (Exception e) {
            throw new RuntimeException("회원가입 로직 실패");
        }
    }

    @Transactional
    public ResponseEntity<?> update(MyUserDetails myUserDetails, UserUpdateReq userUpdateReq) {
        User user = userRepository.findById(myUserDetails.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));

        user.updatePassword(userUpdateReq.getPassword());

        return ResponseEntity.ok().body(new UserUpdateResp(user));
    }
}
