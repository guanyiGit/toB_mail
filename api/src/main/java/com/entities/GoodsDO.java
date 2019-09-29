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
public class GoodsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long goodsId;
	//商品名称
	private String goodsName;
	//状态 1待入库 2已入库 3上架 4下架 5已驳回 6删除
	private Integer status;
	//组织id
	private Integer orgId;
	//商品分类id
	private Integer cid;
	//品牌id
	private Integer brandId;
	//上架时间
	private Date listtingTime;
	//下架时间
	private Date delistingTime;
	//商品编号
	private String goodsNum;
	//备注
	private String remark;
	//描述
	private String description;
	//录入方 1代表供应商 2代表买方
	private Integer operator;
	//商品产地
	private String origin;
	//包装清单
	private String packingList;
	//商品毛重
	private Double weight;
	//供应商id
	private Integer supId;
	//科目id
	private Integer subId;
	//
	private Date createTime;
	//
	private Date updateTime;

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
	 * 设置：商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置：状态 1待入库 2已入库 3上架 4下架 5已驳回 6删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1待入库 2已入库 3上架 4下架 5已驳回 6删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：组织id
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：组织id
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * 设置：商品分类id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：商品分类id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：品牌id
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：品牌id
	 */
	public Integer getBrandId() {
		return brandId;
	}
	/**
	 * 设置：上架时间
	 */
	public void setListtingTime(Date listtingTime) {
		this.listtingTime = listtingTime;
	}
	/**
	 * 获取：上架时间
	 */
	public Date getListtingTime() {
		return listtingTime;
	}
	/**
	 * 设置：下架时间
	 */
	public void setDelistingTime(Date delistingTime) {
		this.delistingTime = delistingTime;
	}
	/**
	 * 获取：下架时间
	 */
	public Date getDelistingTime() {
		return delistingTime;
	}
	/**
	 * 设置：商品编号
	 */
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	/**
	 * 获取：商品编号
	 */
	public String getGoodsNum() {
		return goodsNum;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：录入方 1代表供应商 2代表买方
	 */
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	/**
	 * 获取：录入方 1代表供应商 2代表买方
	 */
	public Integer getOperator() {
		return operator;
	}
	/**
	 * 设置：商品产地
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * 获取：商品产地
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * 设置：包装清单
	 */
	public void setPackingList(String packingList) {
		this.packingList = packingList;
	}
	/**
	 * 获取：包装清单
	 */
	public String getPackingList() {
		return packingList;
	}
	/**
	 * 设置：商品毛重
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * 获取：商品毛重
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * 设置：供应商id
	 */
	public void setSupId(Integer supId) {
		this.supId = supId;
	}
	/**
	 * 获取：供应商id
	 */
	public Integer getSupId() {
		return supId;
	}
	/**
	 * 设置：科目id
	 */
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	/**
	 * 获取：科目id
	 */
	public Integer getSubId() {
		return subId;
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
