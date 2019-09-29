package com.pojo;

import com.entities.GoodsDO;

import java.io.Serializable;
import java.util.List;

public class GoodsInfo implements Serializable {

    private List<SkuInfo> skuDOList;

    private GoodsDO goodsDO;

    public List<SkuInfo> getSkuDOList() {
        return skuDOList;
    }

    public void setSkuDOList(List<SkuInfo> skuDOList) {
        this.skuDOList = skuDOList;
    }

    public GoodsDO getGoodsDO() {
        return goodsDO;
    }

    public void setGoodsDO(GoodsDO goodsDO) {
        this.goodsDO = goodsDO;
    }
}
