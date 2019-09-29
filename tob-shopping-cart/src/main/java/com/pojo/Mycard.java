package com.pojo;

import com.entities.CardDO;
import com.entities.SupplierDO;
import com.entities.UserInfo;

import java.io.Serializable;
import java.util.List;

public class Mycard implements Serializable {

    private CardDO cardDO;

    private UserInfo userInfo;

    private SupplierDO supplierDO;

    private List<GoodsInfo> goodsInfos;

    public SupplierDO getSupplierDO() {
        return supplierDO;
    }

    public void setSupplierDO(SupplierDO supplierDO) {
        this.supplierDO = supplierDO;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public CardDO getCardDO() {
        return cardDO;
    }

    public void setCardDO(CardDO cardDO) {
        this.cardDO = cardDO;
    }
}
