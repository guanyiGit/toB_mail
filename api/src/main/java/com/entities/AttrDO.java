package com.entities;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author none
 * @email guanyimial@126.com
 * @date 2019-01-09 17:45:27
 */
public class AttrDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer attrId;
	//属性名称
	private String attrName;
	//状态 1有效 2无效
	private Integer status;
	//商品分类id
	private Integer cid;
	//属性分组id
	private Integer groupId;
	//是否sku属性 1是 2不是
	private Integer issku;
	//是否必填 1是 2不是
	private Integer ismust;
	//
	private Date createTime;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	/**
	 * 获取：
	 */
	public Integer getAttrId() {
		return attrId;
	}
	/**
	 * 设置：属性名称
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	/**
	 * 获取：属性名称
	 */
	public String getAttrName() {
		return attrName;
	}
	/**
	 * 设置：状态 1有效 2无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1有效 2无效
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：商品分类id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：商品分类id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：属性分组id
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	/**
	 * 获取：属性分组id
	 */
	public Integer getGroupId() {
		return groupId;
	}
	/**
	 * 设置：是否sku属性 1是 2不是
	 */
	public void setIssku(Integer issku) {
		this.issku = issku;
	}
	/**
	 * 获取：是否sku属性 1是 2不是
	 */
	public Integer getIssku() {
		return issku;
	}
	/**
	 * 设置：是否必填 1是 2不是
	 */
	public void setIsmust(Integer ismust) {
		this.ismust = ismust;
	}
	/**
	 * 获取：是否必填 1是 2不是
	 */
	public Integer getIsmust() {
		return ismust;
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
