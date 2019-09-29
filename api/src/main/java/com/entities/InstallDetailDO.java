package com.entities;

import java.io.Serializable;
import java.util.Date;


/**
 * @author none
 * @email guanyimial@126.com
 * @date 2019-01-09 17:45:28
 */
public class InstallDetailDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //订单商品关联表主键id
    private Long ogId;
    //安装单号
    private String installNo;
    //安装记录表主键id
    private Long installId;
    //本次安装数量
    private Integer installNum;
    //
    private Long serverId;
    //
    private Date createTime;
    //
    private Date updateTime;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：订单商品关联表主键id
     */
    public void setOgId(Long ogId) {
        this.ogId = ogId;
    }

    /**
     * 获取：订单商品关联表主键id
     */
    public Long getOgId() {
        return ogId;
    }

    /**
     * 设置：安装记录表主键id
     */
    public void setInstallId(Long installId) {
        this.installId = installId;
    }

    /**
     * 获取：安装记录表主键id
     */
    public Long getInstallId() {
        return installId;
    }

    /**
     * 设置：本次安装数量
     */
    public void setInstallNum(Integer installNum) {
        this.installNum = installNum;
    }

    /**
     * 获取：本次安装数量
     */
    public Integer getInstallNum() {
        return installNum;
    }

    /**
     * 设置：
     */
    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    /**
     * 获取：
     */
    public Long getServerId() {
        return serverId;
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

    public String getInstallNo() {
        return installNo;
    }

    public void setInstallNo(String installNo) {
        this.installNo = installNo;
    }
}
