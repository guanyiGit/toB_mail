/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: goodsVO
 * Author:   Administrator
 * Date:     2018/12/28 14:52
 * Description: 商品列表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.vo;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品列表〉
 *
 * @author Administrator
 * @create 2018/12/28
 * @since 1.0.0
 */
public class goodsVO {
    private Long goodsId;
    private String goodsNum;
    private String goodsName;
    private String supName;
    private String subName;
    private String orgName;
    private Date createTime;
    private Date listtingTime;
    private Date delistingTime;
    private Integer operator;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getListtingTime() {
        return listtingTime;
    }

    public void setListtingTime(Date listtingTime) {
        this.listtingTime = listtingTime;
    }

    public Date getDelistingTime() {
        return delistingTime;
    }

    public void setDelistingTime(Date delistingTime) {
        this.delistingTime = delistingTime;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }
}
