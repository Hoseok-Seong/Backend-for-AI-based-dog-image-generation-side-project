package com.example.puppicasso.domain.gallery.repository;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    @Query("SELECT g FROM Gallery g WHERE g.userId = :userId")
    List<Gallery> findByUserId(@Param("userId") Long userId);
}
