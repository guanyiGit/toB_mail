package com.vo;

import com.entities.TSubject;

import java.util.List;

public class SubVo extends TSubject {

    /**
     * 添加所属组织id
     */
    private Integer orgId;

    /**
     *当前sub关联的商品类型
     */
    private List<Integer> goodsTypeIds;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public List<Integer> getGoodsTypeIds() {
        return goodsTypeIds;
    }

    public void setGoodsTypeIds(List<Integer> goodsTypeIds) {
        this.goodsTypeIds = goodsTypeIds;
    }
}
