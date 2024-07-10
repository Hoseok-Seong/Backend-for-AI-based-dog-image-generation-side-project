package com.example.puppicasso.domain.userInfo.service;

import com.example.puppicasso.domain.ai.util.ImageUtil;
import com.example.puppicasso.domain.user.dao.UserFindDao;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.userInfo.dao.UserInfoFindDao;
import com.example.puppicasso.domain.userInfo.entity.UserInfo;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoUpdateService {
    private final UserFindDao userFindDao;
    private final UserInfoFindDao userInfoFindDao;

    public UserInfo updateUserInfo(final MyUserDetails myUserDetails, final MultipartFile file) {
        byte[] imageData = ImageUtil.encodeMultipartFileToBase64(file);
        final User user = userFindDao.findById(myUserDetails.getUser().getId());
        final UserInfo userInfo = userInfoFindDao.findByUserId(user.getId());

        userInfo.updateProfilePic(imageData);

        return userInfo;
    }
}
