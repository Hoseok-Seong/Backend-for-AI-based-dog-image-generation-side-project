package com.example.puppicasso.domain.main.service;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.repository.GalleryRepository;
import com.example.puppicasso.domain.main.dto.MainPageResp;
import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.domain.subscription.repository.SubscriptionRepository;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import com.example.puppicasso.domain.userInfo.repository.UserInfoRepository;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainPageDataService {

    private final UserInfoRepository userInfoRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final GalleryRepository galleryRepository;

    public ResponseEntity<?> getMainPageData(MyUserDetails myUserDetails) {
        UserInfo userInfo = userInfoRepository.findByUserId(myUserDetails.getUser().getId());
        Subscription subscription = subscriptionRepository.findTopByUserIdOrderByPriceDesc(myUserDetails.getUser().getId());

        Pageable pageable = PageRequest.of(0, 5); // 첫 번째 페이지에서 5개의 결과를 가져옴
        List<Gallery> galleries = galleryRepository.findByUserIdOrderByCreatedDateDesc(myUserDetails.getUser().getId(), pageable);

        return ResponseEntity.ok().body(new MainPageResp(userInfo, subscription, galleries));
    }
}
