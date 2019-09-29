package com.ordermanagement.vo;

import com.entities.OrderDetailDO;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/12/27 17:53
 * @Version 1.0
 */
public class OrderDetailVO extends OrderDetailDO {

    private String goodsAttr;       //属性
    private String orgName;         //下单人所在组织
    private String name;    //下单人
    private String url;     //大图
    private String thumbnailUrl;    //商品缩略图



    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
