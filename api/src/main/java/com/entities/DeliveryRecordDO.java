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
public class DeliveryRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long createBy;
	//
	private Integer supId;
	//发货单号
	private String sendNo;
	//发货人id
	private Long sendId;
	//发货单状态：0已派单，1商品出库，2运送中，3已签收
	private Integer status;
	//收货人id
	private Long recipientId;
	//发货时间(出库时间)
	private Date deliveryTime;
	//签收时间
	private Date signClosedTime;
	//创建时间
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
	 * 设置：
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：
	 */
	public void setSupId(Integer supId) {
		this.supId = supId;
	}
	/**
	 * 获取：
	 */
	public Integer getSupId() {
		return supId;
	}
	/**
	 * 设置：发货单号
	 */
	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}
	/**
	 * 获取：发货单号
	 */
	public String getSendNo() {
		return sendNo;
	}
	/**
	 * 设置：发货人id
	 */
	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}
	/**
	 * 获取：发货人id
	 */
	public Long getSendId() {
		return sendId;
	}
	/**
	 * 设置：发货单状态：0已派单，1商品出库，2运送中，3已签收
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：发货单状态：0已派单，1商品出库，2运送中，3已签收
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：收货人id
	 */
	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}
	/**
	 * 获取：收货人id
	 */
	public Long getRecipientId() {
		return recipientId;
	}
	/**
	 * 设置：发货时间(出库时间)
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * 获取：发货时间(出库时间)
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * 设置：签收时间
	 */
	public void setSignClosedTime(Date signClosedTime) {
		this.signClosedTime = signClosedTime;
	}
	/**
	 * 获取：签收时间
	 */
	public Date getSignClosedTime() {
		return signClosedTime;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
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
