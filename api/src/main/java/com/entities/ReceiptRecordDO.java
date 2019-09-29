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
public class ReceiptRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//订单商品关联表主键id
	private Long ogId;
	//收件人id
	private Long recipientId;
	//收货记录的状态：0待发货，1已收货
	private Integer status;
	//商品数量
	private Integer num;
	//已收商品数
	private Integer receiveNum;
	//期望送货时间
	private Date deliveryTime;
	//期望安装时间
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
	 * 设置：收件人id
	 */
	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}
	/**
	 * 获取：收件人id
	 */
	public Long getRecipientId() {
		return recipientId;
	}
	/**
	 * 设置：收货记录的状态：0待发货，1已收货
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：收货记录的状态：0待发货，1已收货
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：商品数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：商品数量
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：已收商品数
	 */
	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}
	/**
	 * 获取：已收商品数
	 */
	public Integer getReceiveNum() {
		return receiveNum;
	}
	/**
	 * 设置：期望送货时间
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * 获取：期望送货时间
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * 设置：期望安装时间
	 */
	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
	/**
	 * 获取：期望安装时间
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
