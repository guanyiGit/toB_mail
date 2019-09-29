package com.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BudgetOrderVo implements Serializable{

	private static final long serialVersionUID = -8591310738015168732L;
	
	private List<Integer>subIdList;
	private Integer orgId;
	private Date orderDate;
	public List<Integer> getSubIdList() {
		return subIdList;
	}
	public void setSubIdList(List<Integer> subIdList) {
		this.subIdList = subIdList;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
