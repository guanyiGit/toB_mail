package com.pojo;

import com.entities.OtherFeeDO;

import java.util.Set;

public class OtherCostInfo extends OtherFeeDO {


    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 记录编号
     */
    private String recordNum;

    /**
     * 分类统计信息
     */
    private Set<CostDetail> costDetails;

    public Set<CostDetail> getCostDetails() {
        return costDetails;
    }

    public void setCostDetails(Set<CostDetail> costDetails) {
        this.costDetails = costDetails;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum = recordNum;
    }
}
