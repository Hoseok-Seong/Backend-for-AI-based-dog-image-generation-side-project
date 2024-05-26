package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainService {

    @Transactional(readOnly = true)
    public ResponseEntity<?> getMainScreenData(MyUserDetails myUserDetails) {

        return ResponseEntity.ok().body("");
    }
}
