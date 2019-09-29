package com.entities;

import java.util.Date;

public class TOrgRefSubdefine {
    private Integer id;

    private Integer orgId;

    private Integer subDefineId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getSubDefineId() {
        return subDefineId;
    }

    public void setSubDefineId(Integer subDefineId) {
        this.subDefineId = subDefineId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}