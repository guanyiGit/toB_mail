package com.entities;

import java.io.Serializable;
import java.util.Date;


/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:19:29
 */
public class SkuValueRefDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long svrId;
    //属性值id
    private Long avId;
    //sku id
    private Long skuId;
    //
    private Date createTime;
    //
    private Date updateTime;

    /**
     * 设置：
     */
    public void setSvrId(Long svrId) {
        this.svrId = svrId;
    }

    /**
     * 获取：
     */
    public Long getSvrId() {
        return svrId;
    }

    /**
     * 设置：属性值id
     */
    public void setAvId(Long avId) {
        this.avId = avId;
    }

    /**
     * 获取：属性值id
     */
    public Long getAvId() {
        return avId;
    }

    /**
     * 设置：sku id
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取：sku id
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }
}
