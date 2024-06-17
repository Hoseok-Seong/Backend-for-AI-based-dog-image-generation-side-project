package com.example.puppicasso.domain.gallery.service;

import com.example.puppicasso.domain.gallery.dto.GalleriesScreenResp;
import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.repository.GalleryRepository;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> getGalleriesScreenData(MyUserDetails myUserDetails) {

        List<Gallery> galleries = galleryRepository.findByUserId(myUserDetails.getUser().getId());

        return ResponseEntity.ok().body(new GalleriesScreenResp(galleries));
    }
}
