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
public class BrandDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer brandId;
	//品牌英文名
	private String brandEnName;
	//品牌中文名
	private String brandChName;
	//
	private String description;
	//品牌状态 1有效 2无效
	private Integer brandStatus;
	//
	private Date createTime;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：
	 */
	public Integer getBrandId() {
		return brandId;
	}
	/**
	 * 设置：品牌英文名
	 */
	public void setBrandEnName(String brandEnName) {
		this.brandEnName = brandEnName;
	}
	/**
	 * 获取：品牌英文名
	 */
	public String getBrandEnName() {
		return brandEnName;
	}
	/**
	 * 设置：品牌中文名
	 */
	public void setBrandChName(String brandChName) {
		this.brandChName = brandChName;
	}
	/**
	 * 获取：品牌中文名
	 */
	public String getBrandChName() {
		return brandChName;
	}
	/**
	 * 设置：
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：品牌状态 1有效 2无效
	 */
	public void setBrandStatus(Integer brandStatus) {
		this.brandStatus = brandStatus;
	}
	/**
	 * 获取：品牌状态 1有效 2无效
	 */
	public Integer getBrandStatus() {
		return brandStatus;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
