package com.example.puppicasso.domain.gallery.repository;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
