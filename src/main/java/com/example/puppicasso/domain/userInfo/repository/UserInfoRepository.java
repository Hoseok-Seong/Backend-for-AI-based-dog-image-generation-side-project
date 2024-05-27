package com.example.puppicasso.domain.userInfo.repository;

import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
