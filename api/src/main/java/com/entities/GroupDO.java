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
public class GroupDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer groupId;
	//组名称
	private String groupName;
	//状态 1有效 2无效
	private Integer status;
	//商品分类id
	private Integer cid;
	//
	private Date createTime;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	/**
	 * 获取：
	 */
	public Integer getGroupId() {
		return groupId;
	}
	/**
	 * 设置：组名称
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * 获取：组名称
	 */
	public String getGroupName() {
		return groupName;
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
