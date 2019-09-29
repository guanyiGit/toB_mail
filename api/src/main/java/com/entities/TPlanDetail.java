package com.entities;

import java.util.Date;

public class TPlanDetail {
    private Integer id;

    private Integer planId;

    private Integer planMonth;

    private Integer planQuarter;

    private Double planAmount;

    /**
     * 预算进度
     */
    private Double planRate;

    private Integer type;

    /**
     * 实际进度
     */
    private Double actualUseRate;

    private Double actualUseAmount;

    private Integer createBy;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanMonth() {
        return planMonth;
    }

    public void setPlanMonth(Integer planMonth) {
        this.planMonth = planMonth;
    }

    public Integer getPlanQuarter() {
        return planQuarter;
    }

    public void setPlanQuarter(Integer planQuarter) {
        this.planQuarter = planQuarter;
    }

    public Double getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(Double planAmount) {
        this.planAmount = planAmount;
    }

    public Double getPlanRate() {
        return planRate;
    }

    public void setPlanRate(Double planRate) {
        this.planRate = planRate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getActualUseRate() {
        return actualUseRate;
    }

    public void setActualUseRate(Double actualUseRate) {
        this.actualUseRate = actualUseRate;
    }

    public Double getActualUseAmount() {
        return actualUseAmount;
    }

    public void setActualUseAmount(Double actualUseAmount) {
        this.actualUseAmount = actualUseAmount;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}