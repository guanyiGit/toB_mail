package com.vo;

import java.io.Serializable;
import java.util.Date;

public class OrderVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String code;//商品编号
	private String name;//商品名称
	private String buyMan;//采购人员
	private Date buyDate;//采购日期
	private Integer buyNum;//采购数量
	private Double price;//采购单价
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuyMan() {
		return buyMan;
	}
	public void setBuyMan(String buyMan) {
		this.buyMan = buyMan;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
