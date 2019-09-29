package com.pojo;

import java.util.Date;

//送货管理已派单列表列对象
public class HavesentSingle {
    private Long id;//发货记录表主键id
    private String sendNo;//送货单号
    private Date sendSingleTime;//派单时间
    private Date outboundTime;//出库时间
    private Date signClosedTime;//签收时间
    private String sendName;//送货人
    private String sendPhone;//送货人
    private Long closedId;//收货人id
    private String closedName;//收货人
    private String closedPhone;//收货人
    private String address;//收货地址
    private Integer status;//送货状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public Date getSendSingleTime() {
        return sendSingleTime;
    }

    public void setSendSingleTime(Date sendSingleTime) {
        this.sendSingleTime = sendSingleTime;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getClosedName() {
        return closedName;
    }

    public void setClosedName(String closedName) {
        this.closedName = closedName;
    }

    public String getClosedPhone() {
        return closedPhone;
    }

    public void setClosedPhone(String closedPhone) {
        this.closedPhone = closedPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOutboundTime() {
        return outboundTime;
    }

    public void setOutboundTime(Date outboundTime) {
        this.outboundTime = outboundTime;
    }

    public Date getSignClosedTime() {
        return signClosedTime;
    }

    public void setSignClosedTime(Date signClosedTime) {
        this.signClosedTime = signClosedTime;
    }

    public Long getClosedId() {
        return closedId;
    }

    public void setClosedId(Long closedId) {
        this.closedId = closedId;
    }

    @Override
    public String toString() {
        return "HavesentSingle{" +
                "id=" + id +
                ", sendNo='" + sendNo + '\'' +
                ", sendSingleTime=" + sendSingleTime +
                ", outboundTime=" + outboundTime +
                ", signClosedTime=" + signClosedTime +
                ", sendName='" + sendName + '\'' +
                ", sendPhone='" + sendPhone + '\'' +
                ", closedId=" + closedId +
                ", closedName='" + closedName + '\'' +
                ", closedPhone='" + closedPhone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
