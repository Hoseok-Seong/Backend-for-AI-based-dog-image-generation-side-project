package com.example.puppicasso.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResetPasswordReq {
    @NotBlank(message = "username을 입력해주세요")
    @Size(min = 7, max = 50)
    private String username;
}
