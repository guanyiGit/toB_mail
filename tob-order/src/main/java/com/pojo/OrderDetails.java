package com.pojo;

import com.entities.OrderDO;
import com.entities.OrderDetailDO;

import java.util.List;

public class OrderDetails extends OrderDetailDO {

    private OrderDO orderDO;

    private List<InstallDetailVO> installRecordDOS;
    private List<DeliveryDetailVO> deliveryRecordDOS;

    public OrderDO getOrderDO() {
        return orderDO;
    }

    public void setOrderDO(OrderDO orderDO) {
        this.orderDO = orderDO;
    }

    public List<InstallDetailVO> getInstallRecordDOS() {
        return installRecordDOS;
    }

    public void setInstallRecordDOS(List<InstallDetailVO> installRecordDOS) {
        this.installRecordDOS = installRecordDOS;
    }

    public List<DeliveryDetailVO> getDeliveryRecordDOS() {
        return deliveryRecordDOS;
    }

    public void setDeliveryRecordDOS(List<DeliveryDetailVO> deliveryRecordDOS) {
        this.deliveryRecordDOS = deliveryRecordDOS;
    }
}
