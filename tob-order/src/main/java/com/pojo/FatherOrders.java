package com.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//父订单
public class FatherOrders {
    private Long orderId;//订单主键id
    private String orderNo;//订单编号
    private Long buyerId;//下单人id
    private String buyer;//下单人姓名
    private Date createTime;//创建时间
    private BigDecimal excess;//预算超额
    private BigDecimal totalAmount;//总金额
    private Long orgId;//订单所属公司id
    private String company;//订单所属公司
    private Integer status;//订单状态
    private Integer isUseful;//判断订单是否有效0无效1有效
    private Long subId;//订单所属科目id
    private String subName;//订单所属科目名称
    private List<GoodsInfo> goodss;//商品信息
    private List<ChildOrders> childOrders;//子订单信息

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getExcess() {
        return excess;
    }

    public void setExcess(BigDecimal excess) {
        this.excess = excess;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<GoodsInfo> getGoodss() {
        return goodss;
    }

    public void setGoodss(List<GoodsInfo> goodss) {
        this.goodss = goodss;
    }

    public List<ChildOrders> getChildOrders() {
        return childOrders;
    }

    public void setChildOrders(List<ChildOrders> childOrders) {
        this.childOrders = childOrders;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsUseful() {
        return isUseful;
    }

    public void setIsUseful(Integer isUseful) {
        this.isUseful = isUseful;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "FatherOrders{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", buyerId=" + buyerId +
                ", buyer='" + buyer + '\'' +
                ", createTime=" + createTime +
                ", excess=" + excess +
                ", totalAmount=" + totalAmount +
                ", orgId=" + orgId +
                ", company='" + company + '\'' +
                ", status=" + status +
                ", isUseful=" + isUseful +
                ", subId=" + subId +
                ", subName='" + subName + '\'' +
                ", goodss=" + goodss +
                ", childOrders=" + childOrders +
                '}';
    }
}
