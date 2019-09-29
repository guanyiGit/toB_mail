package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author none
 * @email guanyimial@126.com
 * @date 2019-01-09 17:45:28
 */
public class InstallFeeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//安装详情表主键id
	private Long detailId;
	//父订单id或没有子订单的有效订单
	private Long orderId;
	//费用类型（对应字典表id）
	private Integer feeType;
	//费用
	private BigDecimal money;
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
	 * 设置：安装详情表主键id
	 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	/**
	 * 获取：安装详情表主键id
	 */
	public Long getDetailId() {
		return detailId;
	}
	/**
	 * 设置：父订单id或没有子订单的有效订单
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：父订单id或没有子订单的有效订单
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * 设置：费用类型（对应字典表id）
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	/**
	 * 获取：费用类型（对应字典表id）
	 */
	public Integer getFeeType() {
		return feeType;
	}
	/**
	 * 设置：费用
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：费用
	 */
	public BigDecimal getMoney() {
		return money;
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
