package com.sspu.domain.collect;

public class Collect {
    private Integer userId;
    private String commodityId;
    private String name;
    private String flowerLanguage;
    private String picture;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlowerLanguage() {
        return flowerLanguage;
    }

    public void setFlowerLanguage(String flowerLanguage) {
        this.flowerLanguage = flowerLanguage;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
