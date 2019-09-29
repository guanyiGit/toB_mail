package com.pojo;

import java.util.Date;

public class ReceivingRecords {
    private Long id;//收货记录表主键id
    private Long recipientId;//收货人id
    private String name;//收货人姓名
    private String phone;//
    private String address;
    private Integer num;//送货数量
    private Date deliveryTime;//期望送货时间
    private Date installTime;//期望安装时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    @Override
    public String toString() {
        return "ReceivingRecords{" +
                "id=" + id +
                ", recipientId=" + recipientId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", num=" + num +
                ", deliveryTime=" + deliveryTime +
                ", installTime=" + installTime +
                '}';
    }
}
