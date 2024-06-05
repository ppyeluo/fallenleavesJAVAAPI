package com.sspu.domain.customized;

public class HotSaleCommodity {
    private String name;
    private Integer dealTotal;
    private Integer sold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDealTotal() {
        return dealTotal;
    }

    public void setDealTotal(Integer dealTotal) {
        this.dealTotal = dealTotal;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }
}
