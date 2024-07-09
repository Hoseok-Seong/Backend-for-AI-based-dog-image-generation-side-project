package com.example.puppicasso.domain.ai.controller;

import com.example.puppicasso.domain.ai.dto.AIImageReq;
import com.example.puppicasso.domain.ai.dto.AIImageResp;
import com.example.puppicasso.domain.ai.exception.MaxImageSizeExceededException;
import com.example.puppicasso.domain.ai.prompt.Atmosphere;
import com.example.puppicasso.domain.ai.prompt.StudioConcept;
import com.example.puppicasso.domain.ai.service.PictureCreatePageDataService;
import com.example.puppicasso.domain.ai.service.AIService;
import com.example.puppicasso.domain.ai.service.ImageFileService;
import com.example.puppicasso.domain.ai.util.ImageUtil;
import com.example.puppicasso.domain.ai.util.JsonUtil;
import com.example.puppicasso.domain.ai.util.PictureEditUtil;
import com.example.puppicasso.domain.ai.util.PromptUtil;
import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.entity.Type;
import com.example.puppicasso.domain.gallery.dao.GalleryRepository;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
    private final ImageFileService imageFileService;
    private final GalleryRepository galleryRepository;
    private final PictureCreatePageDataService pictureCreatePageDataService;

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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Request to external API failed");
        }
    }

    @PostMapping("/api/models-lab/images")
    public ResponseEntity<?> generateModelsLabImages(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                                     @RequestPart("image") MultipartFile file,
                                                     @RequestPart("details") AIImageReq aiImageReq) {
        try {
            // 파일 크기 검사 (4MB 이하)
            if (file.getSize() > 4 * 1024 * 1024) {
                throw new MaxImageSizeExceededException();
            }

            // 이미지 파일을 서버에 저장하고 URL을 생성
            String filePath = imageFileService.saveFile(file);
            String imageUrl = imageFileService.getFileUrl(filePath);

            // 프롬프트 생성
            String prompt = PromptUtil.generateModelsLabPrompt(aiImageReq);

            String response = aiService.generateModelsLabImages(imageUrl, prompt);

            // JSON 응답에서 이미지 URL 추출
            String generatedImageUrl = JsonUtil.extractImageUrl(response);

            // 이미지 이름 생성
            String imageName = ImageUtil.generateUniqueFileName(myUserDetails.getUser().getId());

            // 생성된 이미지 byte 코드로 변환
            byte[] imageData = ImageUtil.downloadImageAsBase64(generatedImageUrl);

            // Gallery 생성
            Gallery gallery = Gallery.builder()
                    .userId(myUserDetails.getUser().getId())
                    .name(imageName)
                    .type(Type.AI_GENERATED)
                    .data(imageData)
                    .build();
            
            // DB에 저장
            galleryRepository.save(gallery);

            // 파일 삭제
            imageFileService.deleteFile(filePath);

            return ResponseEntity.ok().body(new AIImageResp(gallery, generatedImageUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Image upload failed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Request to external API failed");
        }
    }

    @PostMapping("/api/picture-create")
    public ResponseEntity<?> pictureCreateScreen(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return pictureCreatePageDataService.getPictureCreatePageData(myUserDetails);
    }
}
