package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author none
 * @email guanyimial@126.com
 * @date 2019-01-09 17:45:28
 */
public class OrderDetailDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //
    private Long orderId;
    //
    private String orderNo;
    //创建人(采购人员)所在部门id
    private Long orgId;
    //商品id
    private Long goodsId;
    //商品skuid
    private Long skuId;
    //科目
    private Long subjects;
    //商品购买数量
    private Long totalNum;
    //已收货数量
    private Integer receiveNum;
    //商品单价
    private BigDecimal price;
    //状态
    private Integer status;
    //备注
    private String remark;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置：
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取：
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置：创建人(采购人员)所在部门id
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取：创建人(采购人员)所在部门id
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 设置：商品id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置：商品skuid
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取：商品skuid
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * 设置：科目
     */
    public void setSubjects(Long subjects) {
        this.subjects = subjects;
    }

    /**
     * 获取：科目
     */
    public Long getSubjects() {
        return subjects;
    }

    /**
     * 设置：商品购买数量
     */
    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 获取：商品购买数量
     */
    public Long getTotalNum() {
        return totalNum;
    }

    /**
     * 设置：已收货数量
     */
    public void setReceiveNum(Integer receiveNum) {
        this.receiveNum = receiveNum;
    }

    /**
     * 获取：已收货数量
     */
    public Integer getReceiveNum() {
        return receiveNum;
    }

    /**
     * 设置：商品单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：商品单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
}
