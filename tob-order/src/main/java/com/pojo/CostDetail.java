package com.pojo;

import java.math.BigDecimal;

public class CostDetail {

    /**
     * @param type 费用类型
     * //     *             0：订单额外费用
     * 1：安装额外费用
     * 2：送货额外费用
     */
    private Integer category;
    /**
     * 费用类型，费用
     * 0其他费用,
     * 1安装费,
     * 2运费,
     * 3耗材费,
     * 4高空作业费
     */
    private Integer costType;
    /**
     * 费用金额
     */
    private BigDecimal sum;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
