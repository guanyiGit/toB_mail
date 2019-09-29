/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BudgetVO
 * Author:   Administrator
 * Date:     2019/1/10 14:31
 * Description: 计划新增VO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.vo;

import com.entities.TExecutionPlan;
import com.entities.TPlanDetail;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈计划新增VO〉
 *
 * @author Administrator
 * @create 2019/1/10
 * @since 1.0.0
 */
public class BudgetVO {
    private TExecutionPlan plan;
    private List<TPlanDetail>  planDetailList;

    public TExecutionPlan getPlan() {
        return plan;
    }

    public void setPlan(TExecutionPlan plan) {
        this.plan = plan;
    }

    public List<TPlanDetail> getPlanDetailList() {
        return planDetailList;
    }

    public void setPlanDetailList(List<TPlanDetail> planDetailList) {
        this.planDetailList = planDetailList;
    }
}
