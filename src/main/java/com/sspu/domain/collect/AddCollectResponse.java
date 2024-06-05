package com.sspu.domain.collect;

public class AddCollectResponse {
    private boolean isSuccess;

    public AddCollectResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
