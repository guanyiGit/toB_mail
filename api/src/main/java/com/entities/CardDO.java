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
public class CardDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long cradId;
	//
	private Long goodsId;
	//
	private Long skuId;
	//
	private Integer count;
	//
	private BigDecimal price;
	//
	private Date addTime;
	//0正常，1已下单，2已删除
	private Integer remark;
	//
	private Long userId;
	//商品供应商id
	private Integer supId;

	/**
	 * 设置：
	 */
	public void setCradId(Long cradId) {
		this.cradId = cradId;
	}
	/**
	 * 获取：
	 */
	public Long getCradId() {
		return cradId;
	}
	/**
	 * 设置：
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：
	 */
	public Long getGoodsId() {
		return goodsId;
	}
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
	 * 设置：
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：
	 */
	public Integer getCount() {
		return count;
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
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：0正常，1已下单，2已删除
	 */
	public void setRemark(Integer remark) {
		this.remark = remark;
	}
	/**
	 * 获取：0正常，1已下单，2已删除
	 */
	public Integer getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：商品供应商id
	 */
	public void setSupId(Integer supId) {
		this.supId = supId;
	}
	/**
	 * 获取：商品供应商id
	 */
	public Integer getSupId() {
		return supId;
	}

    @Override
    public String toString() {
        return "CardDO{" +
                "cradId=" + cradId +
                ", goodsId=" + goodsId +
                ", skuId=" + skuId +
                ", count=" + count +
                ", price=" + price +
                ", addTime=" + addTime +
                ", remark=" + remark +
                ", userId=" + userId +
                ", supId=" + supId +
                '}';
    }
}
