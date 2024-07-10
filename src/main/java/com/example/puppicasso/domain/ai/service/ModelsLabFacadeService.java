package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.domain.ai.dto.AIImageReq;
import com.example.puppicasso.domain.ai.dto.AIImageResp;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ModelsLabFacadeService {

    private final ImageFileService imageFileService;
    private final ModelsLabPromptGenerateService modelsLabPromptGenerateService;
    private final ModelsLabAIImageGenerateService modelsLabAIImageGenerateService;
    private final AIImageToEntityService aiImageToEntityService;

    public AIImageResp generateModelsLabImages(final MyUserDetails myUserDetails, final MultipartFile file, final AIImageReq aiImageReq) {

        String reqImageUrl = imageFileService.saveFileAndGetFileUrl(file);

        String prompt = modelsLabPromptGenerateService.generateModelsLabPrompt(aiImageReq);

        String modelsLabResponse = modelsLabAIImageGenerateService.generateModelsLabAIImage(reqImageUrl, prompt);

        imageFileService.deleteFile(reqImageUrl);

        return aiImageToEntityService.saveAIImageToEntity(modelsLabResponse, myUserDetails);
    }
}
