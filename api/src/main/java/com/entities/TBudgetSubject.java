package com.entities;

import java.util.Date;

public class TBudgetSubject {
    private Integer id;

    private String budgetSubjectName;

    private Integer createBy;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBudgetSubjectName() {
        return budgetSubjectName;
    }

    public void setBudgetSubjectName(String budgetSubjectName) {
        this.budgetSubjectName = budgetSubjectName == null ? null : budgetSubjectName.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}