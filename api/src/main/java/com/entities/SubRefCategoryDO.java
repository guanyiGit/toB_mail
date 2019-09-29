package com.entities;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author none
 * @email guanyimial@126.com
 * @date 2019-01-09 17:45:29
 */
public class SubRefCategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer subId;
	//
	private Integer cId;
	//
	private Integer orgId;
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
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	/**
	 * 获取：
	 */
	public Integer getSubId() {
		return subId;
	}
	/**
	 * 设置：
	 */
	public void setCId(Integer cId) {
		this.cId = cId;
	}
	/**
	 * 获取：
	 */
	public Integer getCId() {
		return cId;
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
