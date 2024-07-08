package com.example.puppicasso.domain.token.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewAccessTokenReq {
    private String refreshToken;
    private Long userId;
}
