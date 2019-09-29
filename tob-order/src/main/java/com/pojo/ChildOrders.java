package com.pojo;

import java.util.List;

//子订单信息
public class ChildOrders {
    private Long orderId;//子订单主键id
    private String orderNo;//子订单号
    private Long orgId;//子订单所属公司id
    private Integer status;//订单状态
    private String company;//子订单所属公司
    List<GoodsInfo> goodss;//商品列表


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

    public List<GoodsInfo> getGoodss() {
        return goodss;
    }

    public void setGoodss(List<GoodsInfo> goodss) {
        this.goodss = goodss;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChildOrders{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", orgId=" + orgId +
                ", status=" + status +
                ", company='" + company + '\'' +
                ", goodss=" + goodss +
                '}';
    }
}
