package com.example.puppicasso.domain.userInfo.repository;

import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query("SELECT ui FROM UserInfo ui WHERE ui.userId = :userId")
    UserInfo findByUserId(@Param("userId") Long userId);
}
