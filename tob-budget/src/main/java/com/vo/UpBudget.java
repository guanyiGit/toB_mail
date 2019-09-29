package com.vo;

import java.util.List;

public class UpBudget {

	private Integer orgId;
	
	private String orgName;
	
	private Double totalAmount;
	
	private Integer budgetYear;
	
	private Integer effectiveMonth;
	
	private Integer pId;
	
	private Integer planId;
	
	private List<ChildSub> childSubs;

	
	
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

	public Integer getEffectiveMonth() {
		return effectiveMonth;
	}

	public void setEffectiveMonth(Integer effectiveMonth) {
		this.effectiveMonth = effectiveMonth;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<ChildSub> getChildSubs() {
		return childSubs;
	}

	public void setChildSubs(List<ChildSub> childSubs) {
		this.childSubs = childSubs;
	}

	
	
	
	

	
}
