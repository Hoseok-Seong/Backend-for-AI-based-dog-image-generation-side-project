package com.example.puppicasso.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateProfileReq {
    @NotBlank(message = "password를 입력해주세요")
    @Size(min = 8, max = 50)
    private String password;

    public UserUpdateProfileReq(String password)
    {
        this.password = password;
    }
}
