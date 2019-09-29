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
public class EvaluationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//评价人id
	private Long createBy;
	//商品id
	private Long goodsId;
	//商品skuid
	private Long skuId;
	//评价类型
	private Integer type;
	//评价内容
	private String content;
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
	 * 设置：评价人id
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：评价人id
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：商品id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品id
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：商品skuid
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	/**
	 * 获取：商品skuid
	 */
	public Long getSkuId() {
		return skuId;
	}
	/**
	 * 设置：评价类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：评价类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：评价内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评价内容
	 */
	public String getContent() {
		return content;
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
