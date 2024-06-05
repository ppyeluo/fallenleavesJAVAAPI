package com.sspu.domain.commodity;

import java.util.Date;

public class Commodity {
    private String id;
    private String name;
    private String desc;
    private double price;
    private String materials;
    private String flowerLanguage;
    private String picture;
    private String type;
    private double size;
    private String sold;
    private double score;
    private Integer bank;
    private Integer comments;
    private Date listing_time;


    public Commodity(String id, String name, String desc, double price, String materials, String flowerLanguage, String picture, String type, double size, String sold, double score, Integer bank, Integer comments, Date listing_time) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.materials = materials;
        this.flowerLanguage = flowerLanguage;
        this.picture = picture;
        this.type = type;
        this.size = size;
        this.sold = sold;
        this.score = score;
        this.bank = bank;
        this.comments = comments;
        this.listing_time = listing_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Date getListing_time() {
        return listing_time;
    }

    public void setListing_time(Date listing_time) {
        this.listing_time = listing_time;
    }

}
