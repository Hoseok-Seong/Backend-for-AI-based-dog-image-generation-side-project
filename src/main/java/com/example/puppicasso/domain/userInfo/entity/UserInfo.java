package com.example.puppicasso.domain.userInfo.entity;

import com.example.puppicasso.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user_info")
public class UserInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Lob
    @Column(name = "profile_pic")
    private byte[] profilePic;

    private String grade;

    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Builder
    public UserInfo(Long id, Long userId, byte[] profilePic, String grade, Long subscriptionId) {
        this.id = id;
        this.userId = userId;
        this.profilePic = profilePic;
        this.grade = grade;
        this.subscriptionId = subscriptionId;
    }
}
