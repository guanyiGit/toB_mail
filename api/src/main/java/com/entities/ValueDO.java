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
public class ValueDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long avId;
	//属性id
	private Integer attrId;
	//属性值
	private String attrValue;
	//1有效 2无效
	private Integer status;
	//
	private Date createTime;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setAvId(Long avId) {
		this.avId = avId;
	}
	/**
	 * 获取：
	 */
	public Long getAvId() {
		return avId;
	}
	/**
	 * 设置：属性id
	 */
	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	/**
	 * 获取：属性id
	 */
	public Integer getAttrId() {
		return attrId;
	}
	/**
	 * 设置：属性值
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	/**
	 * 获取：属性值
	 */
	public String getAttrValue() {
		return attrValue;
	}
	/**
	 * 设置：1有效 2无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1有效 2无效
	 */
	public Integer getStatus() {
		return status;
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
