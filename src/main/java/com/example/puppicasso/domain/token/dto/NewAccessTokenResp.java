package com.example.puppicasso.domain.token.dto;

import com.example.puppicasso.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewAccessTokenResp {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;

    public NewAccessTokenResp(User user, String accessToken, String refreshToken) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
