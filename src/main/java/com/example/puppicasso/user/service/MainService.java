package com.example.puppicasso.user.service;

import com.example.puppicasso.global.security.MyUserDetails;
import com.example.puppicasso.user.dto.UserJoinReq;
import com.example.puppicasso.user.dto.UserJoinResp;
import com.example.puppicasso.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainService {

    @Transactional
    public ResponseEntity<?> getMainScreenData(MyUserDetails myUserDetails) {
        return ResponseEntity.ok().body("");
    }
}
