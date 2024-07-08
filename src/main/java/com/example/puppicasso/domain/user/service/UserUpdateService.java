package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.domain.user.dao.UserFindDao;
import com.example.puppicasso.domain.user.dto.UserUpdateReq;
import com.example.puppicasso.domain.user.dto.UserUpdateResp;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserUpdateService {
    private final UserFindDao userFindDao;

    public ResponseEntity<?> updateProfilePicture(MyUserDetails myUserDetails, UserUpdateReq userUpdateReq) {
        final User user = userFindDao.findById(myUserDetails.getUser().getId());

        return ResponseEntity.ok().body(new UserUpdateResp(user));
    }
}
