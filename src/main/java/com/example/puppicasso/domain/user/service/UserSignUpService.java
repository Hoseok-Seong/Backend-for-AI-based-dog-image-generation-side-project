package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.domain.subscription.entity.Type;
import com.example.puppicasso.domain.subscription.dao.SubscriptionRepository;
import com.example.puppicasso.domain.user.dao.UserRepository;
import com.example.puppicasso.domain.user.dto.UserSignUpReq;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.user.exception.UserDuplicateException;
import com.example.puppicasso.domain.userInfo.entity.Grade;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import com.example.puppicasso.domain.userInfo.dao.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSignUpService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SubscriptionRepository subscriptionRepository;
    private final UserInfoRepository userInfoRepository;

    public User signUp(final String userAgent, final UserSignUpReq userSignUpReq) {
        String rawPassword = userSignUpReq.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
        userSignUpReq.setPassword(encPassword);

        Optional<User> userOptional = userRepository.findByUsername(userSignUpReq.getUsername());

        if (userOptional.isPresent()) {
            throw new UserDuplicateException(userOptional.get());
        }

        User user = userRepository.save(userSignUpReq.toEntity());

        // Welcome 쿠폰(Subscription) 생성
        Subscription welcomeSubscription = Subscription.builder()
                .userId(user.getId())
                .type(Type.WELCOME)
                .build();

        subscriptionRepository.save(welcomeSubscription);

        // UserInfo 생성
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .profilePic(null)
                .grade(Grade.BASIC)
                .build();

        userInfoRepository.save(userInfo);

        return user;
    }
}
