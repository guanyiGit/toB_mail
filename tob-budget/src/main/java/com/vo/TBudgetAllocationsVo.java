package com.vo;

import com.entities.TBudgetAllocations;

public class TBudgetAllocationsVo extends TBudgetAllocations {
	private String orgName;
	private String subName;
	private Double actualUse;//使用预算额
	private Integer type;//0:月度计划。1：季度计划

	private Integer userType;//使用类型 1机构的使用预算额 0部门的使用预算额

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getActualUse() {
		return actualUse;
	}
	public void setActualUse(Double actualUse) {
		this.actualUse = actualUse;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
}
