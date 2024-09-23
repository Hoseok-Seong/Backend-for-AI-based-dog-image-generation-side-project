package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.domain.ai.dto.AIImageResp;
import com.example.puppicasso.domain.ai.util.ImageUtil;
import com.example.puppicasso.domain.ai.util.JsonUtil;
import com.example.puppicasso.domain.gallery.dao.GalleryRepository;
import com.example.puppicasso.domain.gallery.dto.GallerySaveReq;
import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.entity.Type;
import com.example.puppicasso.domain.subscription.dao.SubscriptionDao;
import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AIImageToEntityService {

    private final GalleryRepository galleryRepository;
    private final SubscriptionDao subscriptionDao;

    public AIImageResp saveAIImageToEntity(final String APIResponse, final MyUserDetails myUserDetails) {
        String generatedImageUrl = JsonUtil.extractImageUrl(APIResponse);
        String imageName = ImageUtil.generateUniqueFileName(myUserDetails.getUser().getId());
        Type imageType = Type.AI_GENERATED;

        GallerySaveReq gallerySaveReq = new GallerySaveReq(myUserDetails, imageName, imageType, generatedImageUrl);

        final Gallery gallery = galleryRepository.save(gallerySaveReq.toEntity());

        final Subscription subscription = subscriptionDao.findActiveSubscription(myUserDetails.getUser().getId());
        subscription.increaseUsedGenerateCount();

        return new AIImageResp(gallery, generatedImageUrl);
    }
}
