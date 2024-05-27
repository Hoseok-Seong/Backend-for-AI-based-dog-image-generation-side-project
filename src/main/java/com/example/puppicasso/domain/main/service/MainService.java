package com.example.puppicasso.domain.main.service;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.repository.GalleryRepository;
import com.example.puppicasso.domain.main.dto.MainScreenResp;
import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.domain.subscription.repository.SubscriptionRepository;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import com.example.puppicasso.domain.userInfo.repository.UserInfoRepository;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final UserInfoRepository userInfoRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final GalleryRepository galleryRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> getMainScreenData(MyUserDetails myUserDetails) {

        UserInfo userInfo = userInfoRepository.findByUserId(myUserDetails.getUser().getId());
        Subscription subscription = subscriptionRepository.findTopByUserIdOrderByPriceDesc(myUserDetails.getUser().getId());
        List<Gallery> galleries = galleryRepository.findByUserId(myUserDetails.getUser().getId());

        return ResponseEntity.ok().body(new MainScreenResp(userInfo, subscription, galleries));
    }
}
