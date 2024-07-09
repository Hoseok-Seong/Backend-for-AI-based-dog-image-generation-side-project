package com.example.puppicasso.domain.ai.service;

import com.example.puppicasso.domain.ai.dto.PictureCreatePageDataResp;
import com.example.puppicasso.domain.ai.prompt.DogAttributes;
import com.example.puppicasso.domain.ai.prompt.DogBreed;
import com.example.puppicasso.domain.ai.prompt.DogCoatColor;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PictureCreatePageDataService {

    public PictureCreatePageDataResp getPictureCreatePageData(MyUserDetails myUserDetails) {

        PictureCreatePageDataResp response = new PictureCreatePageDataResp();

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

        return response;
    }

    private List<PictureCreatePageDataResp.Attribute> getAttributes(DogAttributes... attributes) {
        return Arrays.stream(attributes)
                .map(attr -> new PictureCreatePageDataResp.Attribute(attr.getEnglishDescription(), attr.getKoreanDescription()))
                .collect(Collectors.toList());
    }

    private List<PictureCreatePageDataResp.Attribute> getAttributes(DogCoatColor... coatColors) {
        return Arrays.stream(coatColors)
                .map(attr -> new PictureCreatePageDataResp.Attribute(attr.getEnglishDescription(), attr.getKoreanDescription()))
                .collect(Collectors.toList());
    }

    private List<PictureCreatePageDataResp.Attribute> getAttributes(DogBreed... breeds) {
        return Arrays.stream(breeds)
                .map(breed -> new PictureCreatePageDataResp.Attribute(breed.getEnglishName(), breed.getKoreanName()))
                .collect(Collectors.toList());
    }
}
