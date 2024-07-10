package com.example.puppicasso.domain.ai.util;
import org.json.JSONObject;
import org.json.JSONArray;

public class JsonUtil {
    /**
     * JSON 응답에서 이미지 URL을 추출합니다.
     *
     * @param jsonResponse JSON 응답 문자열
     * @return 첫 번째 이미지 URL
     */
    public static String extractImageUrl(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray outputArray = jsonObject.getJSONArray("output");
        return outputArray.getString(0);
    }
}
