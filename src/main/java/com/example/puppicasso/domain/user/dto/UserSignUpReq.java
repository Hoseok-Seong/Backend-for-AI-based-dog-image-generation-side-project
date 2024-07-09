package com.example.puppicasso.domain.user.dto;

import com.example.puppicasso.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpReq {
    @NotBlank(message = "username을 입력해주세요")
    @Size(min = 7, max = 50)
    private String username;
    @NotBlank(message = "password를 입력해주세요")
    @Size(min = 8, max = 50)
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
