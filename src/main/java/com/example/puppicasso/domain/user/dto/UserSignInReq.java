package com.example.puppicasso.domain.user.dto;

import com.example.puppicasso.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInReq {
    @NotBlank(message = "username을 입력해주세요")
    @Size(min = 7, max = 50)
    private String username;
    @NotBlank(message = "password를 입력해주세요")
    @Size(min = 8, max = 50)
    private String password;

    public UserSignInReq(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
