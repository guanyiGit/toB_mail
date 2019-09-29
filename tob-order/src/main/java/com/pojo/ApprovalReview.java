package com.pojo;

import java.util.Date;

//审核审批信息
public class ApprovalReview {
    private Long orderId;//订单id
    private Integer status;//订单状态
    private Long reviewerId;//审核人id
    private Long approverId;//审批人id
    private String reviewerName;//审核人姓名
    private String approverName;//审批人姓名
    private String auditOpinion;//审核意见
    private String approvalOpinion;//审批意见
    private Date auditTime;//审核时间
    private Date approvaTime;//审批时间
    private Integer isExcess;//是否超预算0未超 1超额
    private Integer type;//审核审批类型0审核1审批

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getApprovaTime() {
        return approvaTime;
    }

    public void setApprovaTime(Date approvaTime) {
        this.approvaTime = approvaTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsExcess() {
        return isExcess;
    }

    public void setIsExcess(Integer isExcess) {
        this.isExcess = isExcess;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ApprovalReview{" +
                "orderId=" + orderId +
                ", status=" + status +
                ", reviewerId=" + reviewerId +
                ", approverId=" + approverId +
                ", reviewerName='" + reviewerName + '\'' +
                ", approverName='" + approverName + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                ", approvalOpinion='" + approvalOpinion + '\'' +
                ", auditTime=" + auditTime +
                ", approvaTime=" + approvaTime +
                ", isExcess=" + isExcess +
                ", type=" + type +
                '}';
    }
}
