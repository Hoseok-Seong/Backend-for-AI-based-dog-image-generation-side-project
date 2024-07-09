package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.domain.ai.dto.AIImageResp;
import com.example.puppicasso.domain.ai.util.ImageUtil;
import com.example.puppicasso.domain.ai.util.JsonUtil;
import com.example.puppicasso.domain.gallery.dao.GalleryRepository;
import com.example.puppicasso.domain.gallery.dto.GallerySaveReq;
import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.entity.Type;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AIImageToEntityService {

    private final GalleryRepository galleryRepository;

    public AIImageResp saveAIImageToEntity(final String APIResponse, final MyUserDetails myUserDetails) throws Exception {
        String generatedImageUrl = JsonUtil.extractImageUrl(APIResponse);
        String imageName = ImageUtil.generateUniqueFileName(myUserDetails.getUser().getId());
        Type imageType = Type.AI_GENERATED;
        byte[] imageData = ImageUtil.downloadImageAsBase64(generatedImageUrl);

        GallerySaveReq gallerySaveReq = new GallerySaveReq(myUserDetails, imageName, imageType, imageData);

        final Gallery gallery = galleryRepository.save(gallerySaveReq.toEntity());

        return new AIImageResp(gallery, generatedImageUrl);
    }
}
