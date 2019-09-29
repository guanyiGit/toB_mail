package com.RBAC.domain;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-27 19:00:43
 */
public class UserRefSubDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer userId;
	//
	private Integer subId;
	//
	private Date createTime;

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
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
