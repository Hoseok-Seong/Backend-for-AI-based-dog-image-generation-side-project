package com.example.puppicasso.global.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse {
    private String statusCode;
    private String message;
    private Object data;

    public JsonResponse(String statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public JsonResponse(String statusCode, String message) {
        this(statusCode, message, null);
    }

    public String toJson() throws JsonProcessingException {
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("statusCode", statusCode);
        jsonResponse.put("message", message);
        jsonResponse.put("data", data);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jsonResponse);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
