package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.domain.ai.dto.AIImageReq;
import com.example.puppicasso.domain.ai.util.PromptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModelsLabPromptGenerateService {

    public String generateModelsLabPrompt(final AIImageReq aiImageReq) {
        return PromptUtil.generateModelsLabPromptTheme(aiImageReq);
    }
}
