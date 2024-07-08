package com.example.puppicasso.domain.user.dao;

import com.example.puppicasso.domain.token.entity.RefreshTokenRedis;
import com.example.puppicasso.domain.token.exception.RefreshTokenNotFoundException;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.user.exception.UserNotFoundException;
import com.example.puppicasso.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFindDao {

    private final UserRepository userRepository;

    public User findById(final Long userId) {
        final Optional<User> user = userRepository.findById(userId);
        user.orElseThrow(() -> new UserNotFoundException(userId));
        return user.get();
    }
}
