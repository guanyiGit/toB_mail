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
public class OrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单id
	private Long id;
	//订单编号
	private String orderNo;
	//订单创建人id
	private Long createBy;
	//审核人id
	private Long reviewerId;
	//审批人id
	private Long approverId;
	//订单创建人所在部门id
	private Long orgId;
	//0待审核，1审核通过，2驳回，3待审批，4待接单，5已拒单，6待发货（一个都没发），7已发货（至少发了一个），8待评价（所有货都收到了），9已评价（一个订单的所有的商品评价一次性提交，但每个商品一个评价）10已取消
	private Integer status;
	//订单总金额
	private BigDecimal money;
	//审批意见
	private String approvalOpinion;
	//审核意见
	private String auditOpinion;
	//父级订单id,没有父级时为0
	private Long parentId;
	//审核时间
	private Date auditTime;
	//审批时间
	private Date approvaTime;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//供应商id
	private Integer supplierId;
	//拒绝原因
	private String refusecause;
	//订单拒绝理由
	private String refuse;
	//是否是有效订单。有效：1，无效：0
	private Integer isuseful;

	/**
	 * 设置：订单id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：订单id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：订单创建人id
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：订单创建人id
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：审核人id
	 */
	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}
	/**
	 * 获取：审核人id
	 */
	public Long getReviewerId() {
		return reviewerId;
	}
	/**
	 * 设置：审批人id
	 */
	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}
	/**
	 * 获取：审批人id
	 */
	public Long getApproverId() {
		return approverId;
	}
	/**
	 * 设置：订单创建人所在部门id
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：订单创建人所在部门id
	 */
	public Long getOrgId() {
		return orgId;
	}
	/**
	 * 设置：0待审核，1审核通过，2驳回，3待审批，4待接单，5已拒单，6待发货（一个都没发），7已发货（至少发了一个），8待评价（所有货都收到了），9已评价（一个订单的所有的商品评价一次性提交，但每个商品一个评价）10已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0待审核，1审核通过，2驳回，3待审批，4待接单，5已拒单，6待发货（一个都没发），7已发货（至少发了一个），8待评价（所有货都收到了），9已评价（一个订单的所有的商品评价一次性提交，但每个商品一个评价）10已取消
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：订单总金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：订单总金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：审批意见
	 */
	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	/**
	 * 获取：审批意见
	 */
	public String getApprovalOpinion() {
		return approvalOpinion;
	}
	/**
	 * 设置：审核意见
	 */
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	/**
	 * 获取：审核意见
	 */
	public String getAuditOpinion() {
		return auditOpinion;
	}
	/**
	 * 设置：父级订单id,没有父级时为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级订单id,没有父级时为0
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：审核时间
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	/**
	 * 获取：审核时间
	 */
	public Date getAuditTime() {
		return auditTime;
	}
	/**
	 * 设置：审批时间
	 */
	public void setApprovaTime(Date approvaTime) {
		this.approvaTime = approvaTime;
	}
	/**
	 * 获取：审批时间
	 */
	public Date getApprovaTime() {
		return approvaTime;
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
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：供应商id
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商id
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：拒绝原因
	 */
	public void setRefusecause(String refusecause) {
		this.refusecause = refusecause;
	}
	/**
	 * 获取：拒绝原因
	 */
	public String getRefusecause() {
		return refusecause;
	}
	/**
	 * 设置：订单拒绝理由
	 */
	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}
	/**
	 * 获取：订单拒绝理由
	 */
	public String getRefuse() {
		return refuse;
	}
	/**
	 * 设置：是否是有效订单。有效：1，无效：0
有效的含义：含有父订单的所有子订单以及所有不含有父订单的订单
	 */
	public void setIsuseful(Integer isuseful) {
		this.isuseful = isuseful;
	}
	/**
	 * 获取：是否是有效订单。有效：1，无效：0
有效的含义：含有父订单的所有子订单以及所有不含有父订单的订单
	 */
	public Integer getIsuseful() {
		return isuseful;
	}
}
