package com.example.puppicasso.domain.ai.util;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class ImageUtil {
    /**
     * URL에서 이미지를 다운로드하여 Base64로 인코딩한 byte[]를 반환합니다.
     *
     * @param imageUrl 이미지 URL
     * @return Base64로 인코딩된 이미지 데이터
     * @throws IOException 입력/출력 예외
     */
    public static byte[] downloadImageAsBase64(String imageUrl) {
        try (InputStream in = new URL(imageUrl).openStream()) {
            byte[] imageBytes = IOUtils.toByteArray(in);
            return Base64.getEncoder().encode(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 사용자 ID와 현재 날짜, UUID를 병합하여 고유한 파일 이름을 생성합니다.
     *
     * @param userId 사용자 ID
     * @return 고유한 파일 이름
     */
    public static String generateUniqueFileName(Long userId) {
        // 현재 날짜를 yyyyMMdd 형식으로 포맷팅
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());

        // UUID 생성
        String uuid = UUID.randomUUID().toString();

        // 파일 이름 생성 (예: userId_20240614_abcd1234-efgh-5678-ijkl-9876mnopqrst.png)
        return String.format("%s_%s_%s.png", userId, date, uuid);
    }
}
