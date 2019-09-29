/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BudgetMonthSubVO
 * Author:   Administrator
 * Date:     2019/1/24 15:49
 * Description: 新增月度预算科目
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.vo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈新增月度预算科目〉
 *
 * @author Administrator
 * @create 2019/1/24
 * @since 1.0.0
 */
public class BudgetMonthSubVO {
    private Integer subjectId;//科目
    private Double budgetAmount;//科目预算总额

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }
}
