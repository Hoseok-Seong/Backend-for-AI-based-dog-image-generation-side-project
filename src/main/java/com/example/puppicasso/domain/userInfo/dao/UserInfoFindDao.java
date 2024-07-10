package com.example.puppicasso.domain.userInfo.dao;

import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import com.example.puppicasso.domain.userInfo.exception.UserInfoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoFindDao {

    private final UserInfoRepository userInfoRepository;

    public UserInfo findByUserId(final Long userId) {
        final Optional<UserInfo> userInfo = userInfoRepository.findByUserId(userId);
        userInfo.orElseThrow(() -> new UserInfoNotFoundException(userId));
        return userInfo.get();
    }
}
