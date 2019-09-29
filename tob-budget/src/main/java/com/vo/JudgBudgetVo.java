package com.vo;

import java.io.Serializable;

public class JudgBudgetVo implements Serializable {

	private static final long serialVersionUID = -4770761096717987071L;
	
	private Integer orgId;//组织
	
	private Integer subjectId;//科目
	
	private Double useAmount;//使用金额

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Double getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(Double useAmount) {
		this.useAmount = useAmount;
	}
	
	
	
	
}
