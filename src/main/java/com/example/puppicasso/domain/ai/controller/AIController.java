package com.example.puppicasso.domain.ai.controller;

import com.example.puppicasso.domain.ai.dto.AIImageReq;
import com.example.puppicasso.domain.ai.dto.AIImageResp;
import com.example.puppicasso.domain.ai.dto.PictureCreatePageDataResp;
import com.example.puppicasso.domain.ai.exception.InvalidImageFileException;
import com.example.puppicasso.domain.ai.exception.MaxImageSizeExceededException;
import com.example.puppicasso.domain.ai.exception.TikaIoException;
import com.example.puppicasso.domain.ai.service.ModelsLabFacadeService;
import com.example.puppicasso.domain.ai.service.PictureCreatePageDataService;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AIController {

    private final ModelsLabFacadeService modelsLabFacadeService;
    private final PictureCreatePageDataService pictureCreatePageDataService;
    private final Tika tika;

    @PostMapping("/api/models-lab/images")
    public ResponseEntity<AIImageResp> generateModelsLabImages(@AuthenticationPrincipal final MyUserDetails myUserDetails,
                                                     @RequestPart("image") final MultipartFile file,
                                                     @RequestPart("details") final AIImageReq aiImageReq) {

        // TODO: user의 Subscription을 확인해서 남은 이용권 횟수 검증하는 로직 넣기
        // TODO: SubscriptionDao 만들어야 함
        
        // 파일 크기 검사 (10MB 이하)
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new MaxImageSizeExceededException();
        }

        String mimeType = null;

        try {
            mimeType = tika.detect(file.getInputStream());
        } catch (IOException e) {
            throw new TikaIoException();
        }

        if (!mimeType.startsWith("image/")) {
            throw new InvalidImageFileException();
        }

        final AIImageResp aiImageResp = modelsLabFacadeService.generateModelsLabImages(myUserDetails, file, aiImageReq);
        return ResponseEntity.ok().body(aiImageResp);
    }

    @PostMapping("/api/picture-create")
    public ResponseEntity<PictureCreatePageDataResp> pictureCreateScreen(@AuthenticationPrincipal final MyUserDetails myUserDetails) {
        final PictureCreatePageDataResp pictureCreatePageDataResp = pictureCreatePageDataService.getPictureCreatePageData(myUserDetails);
        return ResponseEntity.ok().body(pictureCreatePageDataResp);
    }
}
