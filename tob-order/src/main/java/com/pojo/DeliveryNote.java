package com.pojo;

import java.util.Date;
import java.util.List;

//新建送货单对象
public class DeliveryNote {
    private Long id;//送货单主键id
    private Long userId;//订单创建人id
    private Long orderId;//订单id
    private Integer supId;//创建人所属供应商id
    private String sendNo;//发货单号
    private Long sendId;//发货人id
    private Integer status;//发货单状态
    private Long recipientId;//收货人id
    private Date createTime;//订单创建时间
    List<DeliverysDetail> deliverysDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<DeliverysDetail> getDeliverysDetails() {
        return deliverysDetails;
    }

    public void setDeliverysDetails(List<DeliverysDetail> deliverysDetails) {
        this.deliverysDetails = deliverysDetails;
    }

    @Override
    public String toString() {
        return "DeliveryNote{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", supId=" + supId +
                ", sendNo='" + sendNo + '\'' +
                ", sendId=" + sendId +
                ", status=" + status +
                ", recipientId=" + recipientId +
                ", createTime=" + createTime +
                ", deliverysDetails=" + deliverysDetails +
                '}';
    }
}
