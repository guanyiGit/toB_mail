package com.vo;

import java.io.Serializable;

public class BudgetDetailVo implements Serializable {

	private static final long serialVersionUID = -6377711794353211247L;
	
	private String subName;//科目名称
	private Integer subjectId;//科目id
	private Double actualUse;//截至今天的预算使用额
	
	private Double yearAmount;//年度预算
	private Double leftYearAmount;//年度可用预算
	
	private Double monthOrSeasonAmount;//月度或者季度预算
	private Double leftMonthOrSeasonAmount;//月度或者季度剩余预算
	
	private Double yearRate;//年度预算额实际使用比例
	private Double planMonthOrSeasonRate;//本月或者本季度计划使用比例
	private Double actualMonthOrSeasonRate;//本月或者本季度实际使用比例
	
	private Integer type; //科目类型
	private Double monthAmount; //月度预算总额
	private Double leftMonthAmount; //月度剩余可用余额
	private Double monthRate; //月度使用比例

	
	public Double getYearRate() {
		return yearRate;
	}
	public void setYearRate(Double yearRate) {
		this.yearRate = yearRate;
	}
	public Double getPlanMonthOrSeasonRate() {
		return planMonthOrSeasonRate;
	}
	public void setPlanMonthOrSeasonRate(Double planMonthOrSeasonRate) {
		this.planMonthOrSeasonRate = planMonthOrSeasonRate;
	}
	public Double getActualMonthOrSeasonRate() {
		return actualMonthOrSeasonRate;
	}
	public void setActualMonthOrSeasonRate(Double actualMonthOrSeasonRate) {
		this.actualMonthOrSeasonRate = actualMonthOrSeasonRate;
	}
	public Double getYearAmount() {
		return yearAmount;
	}
	public void setYearAmount(Double yearAmount) {
		this.yearAmount = yearAmount;
	}
	public Double getLeftYearAmount() {
		return leftYearAmount;
	}
	public void setLeftYearAmount(Double leftYearAmount) {
		this.leftYearAmount = leftYearAmount;
	}
	public Double getMonthOrSeasonAmount() {
		return monthOrSeasonAmount;
	}
	public void setMonthOrSeasonAmount(Double monthOrSeasonAmount) {
		this.monthOrSeasonAmount = monthOrSeasonAmount;
	}
	public Double getLeftMonthOrSeasonAmount() {
		return leftMonthOrSeasonAmount;
	}
	public void setLeftMonthOrSeasonAmount(Double leftMonthOrSeasonAmount) {
		this.leftMonthOrSeasonAmount = leftMonthOrSeasonAmount;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Double getActualUse() {
		return actualUse;
	}
	public void setActualUse(Double actualUse) {
		this.actualUse = actualUse;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getMonthAmount() {
		return monthAmount;
	}

	public void setMonthAmount(Double monthAmount) {
		this.monthAmount = monthAmount;
	}

    public Double getLeftMonthAmount() {
        return leftMonthAmount;
    }

    public void setLeftMonthAmount(Double leftMonthAmount) {
        this.leftMonthAmount = leftMonthAmount;
    }

	public Double getMonthRate() {
		return monthRate;
	}

	public void setMonthRate(Double monthRate) {
		this.monthRate = monthRate;
	}
}
