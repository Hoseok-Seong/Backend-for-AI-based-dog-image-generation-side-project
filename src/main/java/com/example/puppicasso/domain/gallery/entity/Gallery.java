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
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Lob
    @Column(name = "data", nullable = false)
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
