package com.example.puppicasso.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewAccessTokenReq {
    private String refreshToken;
    private Long userId;
}
