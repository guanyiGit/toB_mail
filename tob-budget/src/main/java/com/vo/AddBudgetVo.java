package com.vo;

import java.util.List;


public class AddBudgetVo {

	private Integer pId;//当前登录人的组织id
	private Integer budgetYear;//预算年份
	private Integer effectiveMonth;//生效月份
	private Integer orgId;//分配部门组织
	private List<UpBudget> list;
	
	
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
	public List<UpBudget> getList() {
		return list;
	}
	public void setList(List<UpBudget> list) {
		this.list = list;
	}
	
	
}
