package com.pojo;

import com.entities.CardDO;

import java.util.List;

/**
 * @Description
 * @Author: linchong
 * @Date: 2019/1/3 11:07
 * @Version 1.0
 */
public class CardVO {
    private String picUrl;  //图片url
    private String thumbnailUrl;    //压缩图片url
    private Integer buyNum; //采购量
    private Integer browerNum;  //浏览量
    private String attrName;    //商品的名称及属性组合
    private Long subId;     //商品所属的科目id
    private CardDO cardDO;
    private List<RecevieInfo> recevieInfos;      //商品的安装/送货信息

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Integer getBrowerNum() {
        return browerNum;
    }

    public void setBrowerNum(Integer browerNum) {
        this.browerNum = browerNum;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public CardDO getCardDO() {
        return cardDO;
    }

    public void setCardDO(CardDO cardDO) {
        this.cardDO = cardDO;
    }

    public List<RecevieInfo> getRecevieInfos() {
        return recevieInfos;
    }

    public void setRecevieInfos(List<RecevieInfo> recevieInfos) {
        this.recevieInfos = recevieInfos;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "CardVO{" +
                "picUrl='" + picUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", buyNum=" + buyNum +
                ", browerNum=" + browerNum +
                ", attrName='" + attrName + '\'' +
                ", subId=" + subId +
                ", cardDO=" + cardDO +
                ", recevieInfos=" + recevieInfos +
                '}';
    }
}
