package com.pojo;

import java.util.Date;

//新增送货单详情参数对象
public class DeliverysDetail {
    private Long deliveryId;//送货单主键id
    private Long orderId;//订单id
    private Date createTime;//订单创建时间
    private Long ogId;
    private Integer realNum;//发货数量
    private Long sendId;//发货人id
    private Long recipientId;//收货人id

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOgId() {
        return ogId;
    }

    public void setOgId(Long ogId) {
        this.ogId = ogId;
    }

    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "DeliverysDetail{" +
                "deliveryId=" + deliveryId +
                ", orderId=" + orderId +
                ", createTime=" + createTime +
                ", ogId=" + ogId +
                ", realNum=" + realNum +
                ", sendId=" + sendId +
                ", recipientId=" + recipientId +
                '}';
    }
}
