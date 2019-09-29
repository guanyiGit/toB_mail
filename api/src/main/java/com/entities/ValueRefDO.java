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
public class ValueRefDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long gvrId;
	//属性值id
	private Long avId;
	//goods id
	private Long goodsId;
	//
	private Date createTime;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setGvrId(Long gvrId) {
		this.gvrId = gvrId;
	}
	/**
	 * 获取：
	 */
	public Long getGvrId() {
		return gvrId;
	}
	/**
	 * 设置：属性值id
	 */
	public void setAvId(Long avId) {
		this.avId = avId;
	}
	/**
	 * 获取：属性值id
	 */
	public Long getAvId() {
		return avId;
	}
	/**
	 * 设置：goods id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：goods id
	 */
	public Long getGoodsId() {
		return goodsId;
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
