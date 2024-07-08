package com.example.puppicasso.domain.gallery.service;

import com.example.puppicasso.domain.gallery.dto.GalleryPageResp;
import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.repository.GalleryRepository;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GalleryPageDataService {

    private final GalleryRepository galleryRepository;

    public ResponseEntity<?> getGalleryPageData(MyUserDetails myUserDetails) {
        List<Gallery> galleries = galleryRepository.findByUserId(myUserDetails.getUser().getId());

        return ResponseEntity.ok().body(new GalleryPageResp(galleries));
    }
}
