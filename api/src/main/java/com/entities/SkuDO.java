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
public class SkuDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long skuId;
	//商品id
	private Long goodsId;
	//sku编号
	private String skuNum;
	//sku名称
	private String skuName;
	//价格
	private BigDecimal price;
	//库存
	private Integer stock;
	//1有效 2无效
	private Integer status;
	//
	private Date createTime;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	/**
	 * 获取：
	 */
	public Long getSkuId() {
		return skuId;
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
	 * 设置：sku编号
	 */
	public void setSkuNum(String skuNum) {
		this.skuNum = skuNum;
	}
	/**
	 * 获取：sku编号
	 */
	public String getSkuNum() {
		return skuNum;
	}
	/**
	 * 设置：sku名称
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	/**
	 * 获取：sku名称
	 */
	public String getSkuName() {
		return skuName;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**
	 * 获取：库存
	 */
	public Integer getStock() {
		return stock;
	}
	/**
	 * 设置：1有效 2无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1有效 2无效
	 */
	public Integer getStatus() {
		return status;
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
