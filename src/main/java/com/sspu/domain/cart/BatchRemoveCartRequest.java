package com.sspu.domain.cart;

import java.util.List;

public class BatchRemoveCartRequest {
    private List<String> numberList;

    public List<String> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<String> numberList) {
        this.numberList = numberList;
    }
}
