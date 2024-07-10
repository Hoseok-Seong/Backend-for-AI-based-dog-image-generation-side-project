package com.example.puppicasso.domain.email.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailSendReq {
    private String to;
    private String subject;
    private String message;
}
