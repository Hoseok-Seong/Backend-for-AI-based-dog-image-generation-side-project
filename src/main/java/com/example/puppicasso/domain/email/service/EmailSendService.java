package com.example.puppicasso.domain.email.service;

import com.example.puppicasso.domain.email.templates.EmailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailSendService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(final String to, final EmailTemplate template, final String... args) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(template.getSubject());
        message.setText(template.getMessage(args));
        javaMailSender.send(message);
    }
}
