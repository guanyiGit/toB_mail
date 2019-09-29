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
public class SubjectDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer budSubId;
	//
	private String subName;
	//
	private String subCode;
	//
	private Integer pId;
	//
	private Integer subLevel;
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
	public void setSubName(String subName) {
		this.subName = subName;
	}
	/**
	 * 获取：
	 */
	public String getSubName() {
		return subName;
	}
	/**
	 * 设置：
	 */
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	/**
	 * 获取：
	 */
	public String getSubCode() {
		return subCode;
	}
	/**
	 * 设置：
	 */
	public void setPId(Integer pId) {
		this.pId = pId;
	}
	/**
	 * 获取：
	 */
	public Integer getPId() {
		return pId;
	}
	/**
	 * 设置：
	 */
	public void setSubLevel(Integer subLevel) {
		this.subLevel = subLevel;
	}
	/**
	 * 获取：
	 */
	public Integer getSubLevel() {
		return subLevel;
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
