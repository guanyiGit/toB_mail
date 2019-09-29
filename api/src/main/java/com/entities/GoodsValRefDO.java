package com.entities;

import java.util.Date;

public class GoodsValRefDO {

    private Long gvrId;

    private Long avId;

    private Long goodsId;

    private Date createTime;

    private Date updateTime;

    public Long getGvrId() {
        return gvrId;
    }

    public void setGvrId(Long gvrId) {
        this.gvrId = gvrId;
    }

    public Long getAvId() {
        return avId;
    }

    public void setAvId(Long avId) {
        this.avId = avId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
