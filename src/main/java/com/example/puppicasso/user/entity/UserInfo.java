package com.example.puppicasso.user.entity;

import com.example.puppicasso.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(unique = true, nullable = false)
    private Long userId;
    private String profile_pic;
    private String grade;
    private Long subscriptionId;

    @Builder
    public UserInfo(Long id, Long userId, String profile_pic, String grade, Long subscriptionId) {
        this.id = id;
        this.userId = userId;
        this.profile_pic = profile_pic;
        this.grade = grade;
        this.subscriptionId = subscriptionId;
    }
}
