package com.sspu.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHelper {
    private static final Map<Integer, String> DEFAULT_MESSAGES = new HashMap<>();
    static {
        DEFAULT_MESSAGES.put(200, "ok");
        DEFAULT_MESSAGES.put(400, "请求参数错误");
        DEFAULT_MESSAGES.put(401, "Unauthorized");
        DEFAULT_MESSAGES.put(403, "禁止访问");
        DEFAULT_MESSAGES.put(404, "资源不存在");
        DEFAULT_MESSAGES.put(500, "服务器内部错误");
        DEFAULT_MESSAGES.put(501, "功能未实现");
        DEFAULT_MESSAGES.put(502, "网关错误");
        DEFAULT_MESSAGES.put(503, "服务不可用");
        DEFAULT_MESSAGES.put(504, "网关超时");
    }

    public static ResponseEntity sendResponse(int code, Object body) {
        return sendResponse(code, body, DEFAULT_MESSAGES.getOrDefault(code, ""));
    }

    public static ResponseEntity sendResponse(int code, Object body, String message) {
        return ResponseEntity
                .status(HttpStatus.valueOf(code))
                .body(new ResponseDto(code, message, body));
    }

    private static class ResponseDto {
        private int code;
        private String message;
        private Object data;

        public ResponseDto(int code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }
    }
}