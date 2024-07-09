package com.example.puppicasso.domain.user.dto;

import com.example.puppicasso.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInResp {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;

    public UserSignInResp(User user, String accessToken, String refreshToken) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
