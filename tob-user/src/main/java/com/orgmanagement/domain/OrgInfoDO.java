package com.orgmanagement.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-28 15:35:45
 */
public class OrgInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private  String orgNo;

	private Integer pid;
	//0:财务1：行政
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
	//0.全部，1.预算权，2.决算权
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

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
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
	 * 设置：0:财务1：行政
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：0:财务1：行政
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
	 * 设置：0.预算权，1.决算权
	 */
	public void setPower(Integer power) {
		this.power = power;
	}
	/**
	 * 获取：0.预算权，1.决算权
	 */
	public Integer getPower() {
		return power;
	}
}
