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
public class DeliveryDetailDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//订单商品关联表主键id
	private Long ogId;
	//发货记录表主键id
	private Long deliveryId;
	//
	private Integer realNum;
	//
	private Long sendId;
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
	public void setOgId(Long ogId) {
		this.ogId = ogId;
	}
	/**
	 * 获取：订单商品关联表主键id
	 */
	public Long getOgId() {
		return ogId;
	}
	/**
	 * 设置：发货记录表主键id
	 */
	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
	/**
	 * 获取：发货记录表主键id
	 */
	public Long getDeliveryId() {
		return deliveryId;
	}
	/**
	 * 设置：
	 */
	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}
	/**
	 * 获取：
	 */
	public Integer getRealNum() {
		return realNum;
	}
	/**
	 * 设置：
	 */
	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}
	/**
	 * 获取：
	 */
	public Long getSendId() {
		return sendId;
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
