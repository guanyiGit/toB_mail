package com.pojo;

import com.entities.SupplierDO;

import java.util.List;

/**
 * @Description
 * @Author: linchong
 * @Date: 2019/1/3 11:00
 * @Version 1.0
 */
public class CardRecordVO {
    private SupplierDO supplierDO;
    private List<CardVO> cardDOList;

    public SupplierDO getSupplierDO() {
        return supplierDO;
    }

    public void setSupplierDO(SupplierDO supplierDO) {
        this.supplierDO = supplierDO;
    }

    public List<CardVO> getCardDOList() {
        return cardDOList;
    }

    public void setCardDOList(List<CardVO> cardDOList) {
        this.cardDOList = cardDOList;
    }

    @Override
    public String toString() {
        return "CardRecordVO{" +
                "supplierDO=" + supplierDO +
                ", cardDOList=" + cardDOList +
                '}';
    }
}
