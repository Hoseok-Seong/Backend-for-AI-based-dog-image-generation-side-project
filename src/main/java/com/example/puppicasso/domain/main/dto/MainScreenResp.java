package com.example.puppicasso.domain.main.dto;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.subscription.entity.Subscription;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MainScreenResp {
    private String gradeName;
    private String subscriptionName;
    private int usedGenerateCount;
    private int leftGenerateCount;
    private List<String> fileData;

    public MainScreenResp(UserInfo userInfo, Subscription subscription, List<Gallery> galleries) {
        this.gradeName = userInfo.getGrade().getName();
        this.subscriptionName = subscription.getType().getName();
        this.usedGenerateCount = subscription.getUsedGenerateCount();
        this.leftGenerateCount = subscription.getMaxGenerateCount() - subscription.getUsedGenerateCount();
        this.fileData = galleries.stream()
                .map(gallery -> Base64.getEncoder().encodeToString(gallery.getData()))
                .collect(Collectors.toList());
    }
}
