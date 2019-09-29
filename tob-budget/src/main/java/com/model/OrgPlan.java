package com.model;

import com.entities.ExecutionPlanDO;

import java.util.List;

/**
 * 我的可选用执行计划
 */
public class OrgPlan {

    /**
     * 默认执行计划
     */
    private ExecutionPlanDO defualtExecutionPlanDO;
    
    private ExecutionPlanDO selectPlan;

    public ExecutionPlanDO getSelectPlan() {
		return selectPlan;
	}

	public void setSelectPlan(ExecutionPlanDO selectPlan) {
		this.selectPlan = selectPlan;
	}

	/**
     * 我的执行计划
     */
    private List<ExecutionPlanDO> myExecutionPlanDOS;

    public ExecutionPlanDO getDefualtExecutionPlanDO() {
        return defualtExecutionPlanDO;
    }

    public void setDefualtExecutionPlanDO(ExecutionPlanDO defualtExecutionPlanDO) {
        this.defualtExecutionPlanDO = defualtExecutionPlanDO;
    }

    public List<ExecutionPlanDO> getMyExecutionPlanDOS() {
        return myExecutionPlanDOS;
    }

    public void setMyExecutionPlanDOS(List<ExecutionPlanDO> myExecutionPlanDOS) {
        this.myExecutionPlanDOS = myExecutionPlanDOS;
    }
}
