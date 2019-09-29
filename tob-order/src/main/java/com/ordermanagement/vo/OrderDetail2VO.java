package com.ordermanagement.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/12/29 10:15
 * @Version 1.0
 */
public class OrderDetail2VO {
    //创建时间
    private Date createTime;
    private String orderNo;
    private Long orderId;
    private String orgName;
    private String name;
    private BigDecimal totalMoney;
    private List<OrderDetailVO> orderList;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<OrderDetailVO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDetailVO> orderList) {
        this.orderList = orderList;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
