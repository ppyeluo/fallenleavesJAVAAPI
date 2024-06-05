package com.sspu.domain.user;

public class NewAvatarUrl {
    private Integer code;
    private String message;
    private LData data;
    public static class LData {
        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LData getData() {
        return data;
    }

    public void setData(LData data) {
        this.data = data;
    }
}
