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
public class OtherFeeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long feeId;
	//0其他费用,1安装费,2运费,3耗材费,4高空作业费
	private Integer feeType;
	//关联类型 0订单详情，1安装详情，2送货详情
	private Integer refType;
	//关联id(安装记录详情表,送货详情表,订单详情表)
	private Long detailId;
	//
	private BigDecimal price;
	//
	private Date creationTime;

	/**
	 * 设置：
	 */
	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}
	/**
	 * 获取：
	 */
	public Long getFeeId() {
		return feeId;
	}
	/**
	 * 设置：0其他费用,1安装费,2运费,3耗材费,4高空作业费
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	/**
	 * 获取：0其他费用,1安装费,2运费,3耗材费,4高空作业费
	 */
	public Integer getFeeType() {
		return feeType;
	}
	/**
	 * 设置：关联类型 0订单详情，1安装详情，2送货详情
	 */
	public void setRefType(Integer refType) {
		this.refType = refType;
	}
	/**
	 * 获取：关联类型 0订单详情，1安装详情，2送货详情
	 */
	public Integer getRefType() {
		return refType;
	}
	/**
	 * 设置：关联id(安装记录详情表,送货详情表,订单详情表)
	 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	/**
	 * 获取：关联id(安装记录详情表,送货详情表,订单详情表)
	 */
	public Long getDetailId() {
		return detailId;
	}
	/**
	 * 设置：
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreationTime() {
		return creationTime;
	}
}
