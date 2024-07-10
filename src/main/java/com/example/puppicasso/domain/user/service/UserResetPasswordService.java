package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.domain.email.service.EmailSendService;
import com.example.puppicasso.domain.email.templates.EmailTemplate;
import com.example.puppicasso.domain.user.dao.UserFindDao;
import com.example.puppicasso.domain.user.dto.UserResetPasswordReq;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.user.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserResetPasswordService {
    private final UserFindDao userFindDao;
    private final EmailSendService emailSendService;

    public User resetPassword(final UserResetPasswordReq userResetPasswordReq) {
        final User user = userFindDao.findByUsername(userResetPasswordReq.getUsername());
        String newPassword = PasswordUtil.generateRandomPassword();
        user.updatePassword(newPassword);

        emailSendService.sendEmail(userResetPasswordReq.getUsername(), EmailTemplate.PASSWORD_RESET, newPassword);

        return user;
    }
}
