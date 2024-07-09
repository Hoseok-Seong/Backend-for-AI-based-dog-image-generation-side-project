package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.domain.user.dao.UserFindDao;
import com.example.puppicasso.domain.user.dto.UserUpdateProfileReq;
import com.example.puppicasso.domain.user.dto.UserUpdateProfileResp;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserUpdateProfileService {
    private final UserFindDao userFindDao;

    public User updateProfile(MyUserDetails myUserDetails, UserUpdateProfileReq userUpdateProfileReq) {
        final User user = userFindDao.findById(myUserDetails.getUser().getId());
        return user;
    }
}
