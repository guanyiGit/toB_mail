package com.pojo;

import com.entities.OrderDO;

import java.util.List;

public class OrderVO extends OrderDO {

    private List<OrderVO> children;

    public List<OrderVO> getChildren() {
        return children;
    }

    public void setChildren(List<OrderVO> children) {
        this.children = children;
    }
}
