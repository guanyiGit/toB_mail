package com.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 送货管理列表商品信息类
 */
public class GoodsInfo {
    private Long goId;//订单详情表主键id
    private String orderNo;//订单编号
    private Long skuId;//商品的skuid
    private String goodsNo;//商品编号
    private String goodsName;//商品名称
    private Integer goodsNum;//订单量
    private Integer receiveNum;//已发量
    private String imgurl;//商品图片url
    private BigDecimal price;//商品单价
    private BigDecimal priceTotal;//每件商品的订单总价
    private Integer purchaseNum;//采购量每样商品本单位的购买数量
    private Integer viewsNum;//浏览量
    private List consignees;//收货人集合
    private Long subjects;//商品所属科目

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(Integer receiveNum) {
        this.receiveNum = receiveNum;
    }

    public Long getGoId() {
        return goId;
    }

    public void setGoId(Long goId) {
        this.goId = goId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public Integer getViewsNum() {
        return viewsNum;
    }

    public void setViewsNum(Integer viewsNum) {
        this.viewsNum = viewsNum;
    }

    public List getConsignees() {
        return consignees;
    }

    public void setConsignees(List consignees) {
        this.consignees = consignees;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Long getSubjects() {
        return subjects;
    }

    public void setSubjects(Long subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "goId=" + goId +
                ", orderNo='" + orderNo + '\'' +
                ", skuId=" + skuId +
                ", goodsNo='" + goodsNo + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNum=" + goodsNum +
                ", receiveNum=" + receiveNum +
                ", imgurl='" + imgurl + '\'' +
                ", price=" + price +
                ", priceTotal=" + priceTotal +
                ", purchaseNum=" + purchaseNum +
                ", viewsNum=" + viewsNum +
                ", consignees=" + consignees +
                ", subjects=" + subjects +
                '}';
    }
}
