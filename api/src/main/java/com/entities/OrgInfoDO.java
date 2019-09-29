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
public class OrgInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String orgNo;
	//
	private Integer pid;
	//
	private Integer type;
	//
	private String orgName;
	//
	private Integer orgLevel;
	//
	private Date createDate;
	//
	private Date updateDate;
	//
	private Integer createBy;
	//
	private Integer budSubId;
	//
	private Integer power;

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
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	/**
	 * 获取：
	 */
	public String getOrgNo() {
		return orgNo;
	}
	/**
	 * 设置：
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：
	 */
	public Integer getPid() {
		return pid;
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
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：
	 */
	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}
	/**
	 * 获取：
	 */
	public Integer getOrgLevel() {
		return orgLevel;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateDate() {
		return updateDate;
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
	 * 设置：
	 */
	public void setBudSubId(Integer budSubId) {
		this.budSubId = budSubId;
	}
	/**
	 * 获取：
	 */
	public Integer getBudSubId() {
		return budSubId;
	}
	/**
	 * 设置：
	 */
	public void setPower(Integer power) {
		this.power = power;
	}
	/**
	 * 获取：
	 */
	public Integer getPower() {
		return power;
	}
}
