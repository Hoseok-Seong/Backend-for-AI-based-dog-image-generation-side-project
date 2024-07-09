package com.example.puppicasso.domain.user.dto;

import com.example.puppicasso.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateProfileResp {
    private Long id;
    private String username;
    public UserUpdateProfileResp(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
