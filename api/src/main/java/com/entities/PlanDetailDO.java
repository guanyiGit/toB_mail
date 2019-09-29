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
public class PlanDetailDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer planId;
	//
	private Integer planMonth;
	//
	private Integer planQuarter;
	//
	private Double planAmount;
	//
	private Double planRate;
	//
	private Integer type;
	//
	private Double actualUseRate;
	//
	private Double actualUseAmount;
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
	public void setPlanMonth(Integer planMonth) {
		this.planMonth = planMonth;
	}
	/**
	 * 获取：
	 */
	public Integer getPlanMonth() {
		return planMonth;
	}
	/**
	 * 设置：
	 */
	public void setPlanQuarter(Integer planQuarter) {
		this.planQuarter = planQuarter;
	}
	/**
	 * 获取：
	 */
	public Integer getPlanQuarter() {
		return planQuarter;
	}
	/**
	 * 设置：
	 */
	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}
	/**
	 * 获取：
	 */
	public Double getPlanAmount() {
		return planAmount;
	}
	/**
	 * 设置：
	 */
	public void setPlanRate(Double planRate) {
		this.planRate = planRate;
	}
	/**
	 * 获取：
	 */
	public Double getPlanRate() {
		return planRate;
	}
	/**
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setActualUseRate(Double actualUseRate) {
		this.actualUseRate = actualUseRate;
	}
	/**
	 * 获取：
	 */
	public Double getActualUseRate() {
		return actualUseRate;
	}
	/**
	 * 设置：
	 */
	public void setActualUseAmount(Double actualUseAmount) {
		this.actualUseAmount = actualUseAmount;
	}
	/**
	 * 获取：
	 */
	public Double getActualUseAmount() {
		return actualUseAmount;
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
