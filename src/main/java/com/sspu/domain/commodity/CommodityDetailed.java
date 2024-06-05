package com.sspu.domain.commodity;

import com.sspu.domain.commodity.Commodity;

import java.util.Date;

public class CommodityDetailed extends Commodity {

    String[] imgUrls;

    public CommodityDetailed(String id, String name, String desc, double price, String materials, String flowerLanguage, String picture, String type, double size, String sold, double score, Integer bank, Integer comments, Date listing_time, String[] imgUrls) {
        super(id, name, desc, price, materials, flowerLanguage, picture, type, size, sold, score, bank, comments, listing_time);
        this.imgUrls = imgUrls;
    }

    public String[] getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }
}
