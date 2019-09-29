package com.vo;

import java.math.BigDecimal;

public class ChildSub {
	
	private Integer subId;
	
	private Integer budgetYear;
	private Integer orgId;
	private Integer effectiveMonth;
	private Double subAmount;
	private String subName;
	private Integer pId;
	private Integer planId;
	
	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getEffectiveMonth() {
		return effectiveMonth;
	}

	public void setEffectiveMonth(Integer effectiveMonth) {
		this.effectiveMonth = effectiveMonth;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public Double getSubAmount() {
		return subAmount;
	}

	public void setSubAmount(Double subAmount) {
		this.subAmount = subAmount;
	}


	

}
