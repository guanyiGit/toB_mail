package com.entities;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author none
 * @email guanyimial@126.com
 * @date 2019-01-09 17:45:28
 */
public class BudgetAllocationsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer orgId;
	//
	private Integer planId;
	//
	private Integer subjectId;
	//
	private Integer budgetYear;
	//
	private Integer effectiveMonth;
	//
	private Double budgetAmount;
	//
	private Integer createBy;
	//创建时间
	private Date createDate;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * 设置：
	 */
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	/**
	 * 获取：
	 */
	public Integer getPlanId() {
		return planId;
	}
	/**
	 * 设置：
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * 获取：
	 */
	public Integer getSubjectId() {
		return subjectId;
	}
	/**
	 * 设置：
	 */
	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
	}
	/**
	 * 获取：
	 */
	public Integer getBudgetYear() {
		return budgetYear;
	}
	/**
	 * 设置：
	 */
	public void setEffectiveMonth(Integer effectiveMonth) {
		this.effectiveMonth = effectiveMonth;
	}
	/**
	 * 获取：
	 */
	public Integer getEffectiveMonth() {
		return effectiveMonth;
	}
	/**
	 * 设置：
	 */
	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	/**
	 * 获取：
	 */
	public Double getBudgetAmount() {
		return budgetAmount;
	}
	/**
	 * 设置：
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
}
