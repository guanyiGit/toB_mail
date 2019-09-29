package com.vo;

import com.entities.TSubDefine;
import com.entities.TSubject;

import java.util.List;

public class SubDefineVo extends TSubDefine {

    Integer orgId;

    List<TSubject> subjects;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public List<TSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<TSubject> subjects) {
        this.subjects = subjects;
    }
}
