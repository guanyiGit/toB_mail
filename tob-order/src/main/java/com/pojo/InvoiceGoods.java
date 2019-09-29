package com.pojo;

import java.util.Date;

//发货单商品信息
public class InvoiceGoods {
    private Long goId;//订单详情表主键id
    private String orderNo;//订单号
    private Long goodsNo;//商品编号
    private String goodsName;//商品名称
    private Integer goodsNum;//订单量
    private Integer receiveNum;//已发量

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

    public Long getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Long goodsNo) {
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

    @Override
    public String toString() {
        return "InvoiceGoods{" +
                "goId=" + goId +
                ", orderNo='" + orderNo + '\'' +
                ", goodsNo=" + goodsNo +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNum=" + goodsNum +
                ", receiveNum=" + receiveNum +
                '}';
    }
}
