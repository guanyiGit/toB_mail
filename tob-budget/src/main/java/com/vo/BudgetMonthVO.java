/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BudgetMonthVO
 * Author:   Administrator
 * Date:     2019/1/24 15:30
 * Description: 新增月度预算
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.vo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈新增月度预算〉
 *
 * @author Administrator
 * @create 2019/1/24
 * @since 1.0.0
 */
public class BudgetMonthVO {
    private Integer pId;//当前登录人的组织id
    private Integer budgetYear;//预算年份
    private Integer budgetMonth;//预算月份
    private Integer orgId;//分配部门组织
    private Double budgetAmount;//预算总额
    private List<BudgetMonthSubVO> budgetMonthSubList;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(Integer budgetYear) {
        this.budgetYear = budgetYear;
    }

    public Integer getBudgetMonth() {
        return budgetMonth;
    }

    public void setBudgetMonth(Integer budgetMonth) {
        this.budgetMonth = budgetMonth;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public List<BudgetMonthSubVO> getBudgetMonthSubList() {
        return budgetMonthSubList;
    }

    public void setBudgetMonthSubList(List<BudgetMonthSubVO> budgetMonthSubList) {
        this.budgetMonthSubList = budgetMonthSubList;
    }
}
