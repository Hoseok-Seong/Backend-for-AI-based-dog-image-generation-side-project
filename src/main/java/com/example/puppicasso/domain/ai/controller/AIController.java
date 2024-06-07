package com.example.puppicasso.domain.ai.controller;

import com.example.puppicasso.domain.ai.dto.AIImageReq;
import com.example.puppicasso.domain.ai.prompt.Atmosphere;
import com.example.puppicasso.domain.ai.prompt.StudioConcept;
import com.example.puppicasso.domain.ai.service.AIService;
import com.example.puppicasso.domain.ai.util.PictureEditUtil;
import com.example.puppicasso.domain.ai.util.PromptUtil;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/api/OpenAI/Images")
    public ResponseEntity<?> generateOpenAIImages(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                                @RequestParam("image") MultipartFile file,
                                                @RequestParam("atmosphere") String atmosphereName,
                                                @RequestParam("studio") String studioName) {
        try {
            // 파일 크기 검사 (4MB 이하)
            if (file.getSize() > 4 * 1024 * 1024) {
                return ResponseEntity.badRequest().body("Image must be less than 4MB.");
            }

            // 이미지를 BufferedImage로 변환
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            // 이미지가 PNG 형식이 아닌 경우 PNG로 변환
            if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".png")) {
                bufferedImage = PictureEditUtil.convertToPng(bufferedImage);
            }

            // 이미지에 투명도가 없는 경우 투명 배경 추가
            if (!PictureEditUtil.hasTransparency(bufferedImage)) {
                bufferedImage = PictureEditUtil.addTransparency(bufferedImage);
            }

            // 임시 파일로 저장
            File tempFile = File.createTempFile("upload-", ".png");
            ImageIO.write(bufferedImage, "png", tempFile);

            // Enum으로 변환
            Atmosphere atmosphere;
            StudioConcept studio;
            try {
                atmosphere = Atmosphere.valueOf(atmosphereName.toUpperCase());
                studio = StudioConcept.valueOf(studioName.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid atmosphere or studio concept.");
            }

            // 프롬프트 생성
            String prompt = PromptUtil.generateOpenAIPrompt(atmosphere, studio);

            String response = aiService.generateOpenAIImages(tempFile, prompt);

            // 임시 파일 삭제
            tempFile.delete();
            return ResponseEntity.ok().body(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Image upload failed");
        }
    }

    @PostMapping("/api/ModelsLab/Images")
    public ResponseEntity<?> generateModelsLabImages(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                                     @RequestBody AIImageReq aiImageReq) {
        return ResponseEntity.ok().body("ok");
    }
}
