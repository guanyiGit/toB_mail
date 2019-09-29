package com.vo;

import java.util.Date;

public class TBudgetAllocations2 {

	
	private Integer budgetYear;
	private Integer effectiveMonth;
	
	private Double budgetAmount;
	private Integer id;
	private Integer subjectId;
	private String subName;
	private Date createDate;
	
	private Integer type;//0科目分配;1组织分配
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Double getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
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
	
}
