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
public class InstallRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//订单商品关联表主键id
	private Long oDetailId;
	//创建人id
	private Long createBy;
	//安装人id
	private Long serverId;
	//客户id
	private Long customerId;
	//安装状态
	private Integer status;
	//安装时间
	private Date installTime;
	//
	private Date createTime;
	//
	private Date updateTime;

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
	 * 设置：订单商品关联表主键id
	 */
	public void setODetailId(Long oDetailId) {
		this.oDetailId = oDetailId;
	}
	/**
	 * 获取：订单商品关联表主键id
	 */
	public Long getODetailId() {
		return oDetailId;
	}
	/**
	 * 设置：创建人id
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人id
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：安装人id
	 */
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
	/**
	 * 获取：安装人id
	 */
	public Long getServerId() {
		return serverId;
	}
	/**
	 * 设置：客户id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：客户id
	 */
	public Long getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：安装状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：安装状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：安装时间
	 */
	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
	/**
	 * 获取：安装时间
	 */
	public Date getInstallTime() {
		return installTime;
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
