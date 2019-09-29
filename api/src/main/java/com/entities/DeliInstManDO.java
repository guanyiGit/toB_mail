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
public class DeliInstManDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Integer userId;
	//
	private Integer supplierId;
	//
	private String name;
	//送货，安装人员编号
	private String dimNo;
	//0：送货人员；1：安装人员
	private Integer type;
	//性别
	private Integer sex;
	//手机号
	private String phoneNum;
	//
	private Date createTime;
	//
	private Date updateTime;
	//0启动1禁用
	private Integer state;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：送货，安装人员编号
	 */
	public void setDimNo(String dimNo) {
		this.dimNo = dimNo;
	}
	/**
	 * 获取：送货，安装人员编号
	 */
	public String getDimNo() {
		return dimNo;
	}
	/**
	 * 设置：0：送货人员；1：安装人员
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：0：送货人员；1：安装人员
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhoneNum() {
		return phoneNum;
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
	/**
	 * 设置：0启动1禁用
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：0启动1禁用
	 */
	public Integer getState() {
		return state;
	}
}
