package com.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品信息
 */
public class OrderGoods {
    private Long orderId;//订单id
    private Long parentId;//父订单id
    private Long goId;//订单详情表主键id
    private String orderNo;//订单编号
    private Date createTime;//订单创建时间
    private Long skuId;//商品的skuid
    private String goodsNo;//商品编号
    private String goodsName;//商品名称
    private Integer goodsNum;//订单量(发货记录中的数量)
    private Integer receiveNum;//已发量
    private Long recipientId;//收货人id
    private String recipientName;//收货人姓名
    private String recipientPhone;
    private String recipientAddress;
    private BigDecimal price;//商品单价
    private Long buyerId;//下单人id
    private String buyer;//下单人姓名
    private Long orgId;//订单所属公司id
    private String company;//订单所属公司
    private Integer purchaseNum;//采购量（订单详情中的数量）
    private Integer viewsNum;//浏览量
    private String imgurl;//图片url
    private BigDecimal totalPrice;//商品总价：每件商品的总价
    private String parentNo;//父订单号

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "orderId=" + orderId +
                ", parentId=" + parentId +
                ", goId=" + goId +
                ", orderNo='" + orderNo + '\'' +
                ", createTime=" + createTime +
                ", skuId=" + skuId +
                ", goodsNo='" + goodsNo + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNum=" + goodsNum +
                ", receiveNum=" + receiveNum +
                ", recipientId=" + recipientId +
                ", recipientName='" + recipientName + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", recipientAddress='" + recipientAddress + '\'' +
                ", price=" + price +
                ", buyerId=" + buyerId +
                ", buyer='" + buyer + '\'' +
                ", orgId=" + orgId +
                ", company='" + company + '\'' +
                ", purchaseNum=" + purchaseNum +
                ", viewsNum=" + viewsNum +
                ", imgurl='" + imgurl + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
