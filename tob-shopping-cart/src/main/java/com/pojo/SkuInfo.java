package com.pojo;

import com.entities.OrderDetailDO;
import com.entities.SkuDO;

import java.util.List;

public class SkuInfo extends SkuDO {

    private List<OrderDetailDO> orderDetailDOs;

    /**
     *
     */
    private Long totalCount;

    public List<OrderDetailDO> getOrderDetailDOs() {
        return orderDetailDOs;
    }

    public void setOrderDetailDOs(List<OrderDetailDO> orderDetailDOs) {
        this.orderDetailDOs = orderDetailDOs;
    }

    public Long getTotalCount() {
        this.totalCount = this.getOrderDetailDOs() != null ? Long.valueOf(this.getOrderDetailDOs().size()) : 0;
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
