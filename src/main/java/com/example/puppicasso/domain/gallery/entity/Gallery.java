package com.example.puppicasso.domain.gallery.entity;

import com.example.puppicasso.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "gallery")
public class Gallery extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Builder
    public Gallery(Long id, Long userId, String name, Type type, byte[] data) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.data = data;
        this.isDeleted = false;
    }
}
