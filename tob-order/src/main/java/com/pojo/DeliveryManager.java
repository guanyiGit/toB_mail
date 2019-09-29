package com.pojo;

import java.util.Date;
import java.util.List;

/**
 * 送货管理列表实体类
 */
public class DeliveryManager {
    private Long orderId;//订单id
    private String orderNo;//订单编号
    private Date createTime;//订单创建时间
    private Long recipientId;//收货人id
    private String recipientName;//收货人姓名
    private String recipientPhone;
    private String recipientAddress;
    private List<GoodsInfo> goodsInfos;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DeliveryManager{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", createTime=" + createTime +
                ", recipientId=" + recipientId +
                ", recipientName='" + recipientName + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", recipientAddress='" + recipientAddress + '\'' +
                ", goodsInfos=" + goodsInfos +
                '}';
    }
}
