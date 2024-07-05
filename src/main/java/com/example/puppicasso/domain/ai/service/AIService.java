package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.domain.ai.dto.PictureCreateResp;
import com.example.puppicasso.domain.ai.prompt.DogAttributes;
import com.example.puppicasso.domain.ai.prompt.DogBreed;
import com.example.puppicasso.domain.ai.prompt.DogCoatColor;
import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.main.dto.MainScreenResp;
import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import com.example.puppicasso.global.config.ModelsLabConfig;
import com.example.puppicasso.global.config.OpenAIConfig;
import com.example.puppicasso.global.security.MyUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AIService {

    private final OpenAIConfig openAIConfig;
    private final ModelsLabConfig modelsLabConfig;

    @Transactional
    public String generateOpenAIImages(File imageFile, String prompt) throws IOException {
        HttpHeaders headers = openAIConfig.openAIHttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(imageFile));
        body.add("model", "dall-e-2");
        body.add("prompt", prompt);
        body.add("n", 1);
        body.add("size", "512x512");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send request
        ResponseEntity<String> response = openAIConfig.openAIRestTemplate()
                .exchange(
                        "https://api.openai.com/v1/images/edits",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        return response.getBody();
    }

    @Transactional
    public String generateModelsLabImages(String imageUrl, String prompt) throws IOException {
        HttpHeaders headers = modelsLabConfig.modelsLabHttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("key", modelsLabConfig.getSecretKey());
        requestBody.put("prompt", prompt);
        requestBody.put("negative_prompt", "bad quality");
        requestBody.put("init_image", imageUrl);
        requestBody.put("width", "512");
        requestBody.put("height", "512");
        requestBody.put("samples", "1");
        requestBody.put("temp", false);
        requestBody.put("safety_checker", false);
        requestBody.put("strength", 1.0);
        requestBody.put("seed", null);
        requestBody.put("enhance_prompt", true);
        requestBody.put("webhook", null);
        requestBody.put("track_id", null);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequestBody, headers);

        // Send request
        ResponseEntity<String> response = modelsLabConfig.modelsLabRestTemplate().postForEntity(
                "https://modelslab.com/api/v3/img2img",
                requestEntity,
                String.class
        );

        return response.getBody();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getPictureCreateData(MyUserDetails myUserDetails) {

        PictureCreateResp response = new PictureCreateResp();

        response.setBreeds(getAttributes(DogBreed.values()));
        response.setSizes(getAttributes(DogAttributes.SIZE_SMALL, DogAttributes.SIZE_MEDIUM, DogAttributes.SIZE_LARGE));
        response.setCoatLengths(getAttributes(DogAttributes.COAT_SHORT, DogAttributes.COAT_MEDIUM, DogAttributes.COAT_LONG));
        response.setFurTextures(getAttributes(DogAttributes.FUR_SMOOTH, DogAttributes.FUR_STRAIGHT, DogAttributes.FUR_WAVY, DogAttributes.FUR_CURLY));
        response.setEyeColors(getAttributes(DogAttributes.EYE_BROWN, DogAttributes.EYE_BLUE, DogAttributes.EYE_GREEN, DogAttributes.EYE_AMBER));
        response.setEarShapes(getAttributes(DogAttributes.EAR_TRIANGULAR, DogAttributes.EAR_ROUND, DogAttributes.EAR_DROOPY, DogAttributes.EAR_POINTED));
        response.setNoseShapes(getAttributes(DogAttributes.NOSE_LONG, DogAttributes.NOSE_SHORT, DogAttributes.NOSE_FLAT));
        response.setFaceShapes(getAttributes(DogAttributes.FACE_LONG, DogAttributes.FACE_ROUND, DogAttributes.FACE_SQUARE));
        response.setTailShapes(getAttributes(DogAttributes.TAIL_CURLED, DogAttributes.TAIL_STRAIGHT, DogAttributes.TAIL_BUSHY));
        response.setExpressions(getAttributes(DogAttributes.EXPRESSION_HAPPY, DogAttributes.EXPRESSION_SAD, DogAttributes.EXPRESSION_ALERT, DogAttributes.EXPRESSION_RELAXED));
        response.setPoses(getAttributes(DogAttributes.POSE_SITTING, DogAttributes.POSE_STANDING, DogAttributes.POSE_RUNNING, DogAttributes.POSE_LYING_DOWN));
        response.setCoatColors(getAttributes(DogCoatColor.values()));

        return ResponseEntity.ok().body(response);
    }

    private List<PictureCreateResp.Attribute> getAttributes(DogAttributes... attributes) {
        return Arrays.stream(attributes)
                .map(attr -> new PictureCreateResp.Attribute(attr.getEnglishDescription(), attr.getKoreanDescription()))
                .collect(Collectors.toList());
    }

    private List<PictureCreateResp.Attribute> getAttributes(DogCoatColor... coatColors) {
        return Arrays.stream(coatColors)
                .map(attr -> new PictureCreateResp.Attribute(attr.getEnglishDescription(), attr.getKoreanDescription()))
                .collect(Collectors.toList());
    }

    private List<PictureCreateResp.Attribute> getAttributes(DogBreed... breeds) {
        return Arrays.stream(breeds)
                .map(breed -> new PictureCreateResp.Attribute(breed.getEnglishName(), breed.getKoreanName()))
                .collect(Collectors.toList());
    }
}
