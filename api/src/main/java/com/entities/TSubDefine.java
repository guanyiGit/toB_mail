package com.entities;

import java.util.Date;

public class TSubDefine {
    private Integer id;

    private String subDefineName;

    private String year;

    private Integer createBy;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubDefineName() {
        return subDefineName;
    }

    public void setSubDefineName(String subDefineName) {
        this.subDefineName = subDefineName == null ? null : subDefineName.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
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