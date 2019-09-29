package com.pojo;

import java.util.Date;

/**
 * @Description
 * @Author: linchong
 * @Date: 2019/1/3 17:23
 * @Version 1.0
 */
public class RecevieInfo {
    private Long receiverId;    //接收人id
    private Integer recNum;     //接收数量
    private Date expectRecTime; //期望送货时间
    private Date expectInstTime;    //期望安装时间

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getRecNum() {
        return recNum;
    }

    public void setRecNum(Integer recNum) {
        this.recNum = recNum;
    }

    public Date getExpectRecTime() {
        return expectRecTime;
    }

    public void setExpectRecTime(Date expectRecTime) {
        this.expectRecTime = expectRecTime;
    }

    public Date getExpectInstTime() {
        return expectInstTime;
    }

    public void setExpectInstTime(Date expectInstTime) {
        this.expectInstTime = expectInstTime;
    }

    @Override
    public String toString() {
        return "RecevieInfo{" +
                "receiverId=" + receiverId +
                ", recNum=" + recNum +
                ", expectRecTime=" + expectRecTime +
                ", expectInstTime=" + expectInstTime +
                '}';
    }
}
