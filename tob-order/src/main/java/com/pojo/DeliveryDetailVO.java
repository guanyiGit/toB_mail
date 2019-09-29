package com.pojo;

import com.entities.DeliInstManDO;
import com.entities.DeliveryDetailDO;
import com.entities.DeliveryRecordDO;

public class DeliveryDetailVO extends DeliveryDetailDO {

    private DeliInstManDO deliInstManDO;

    private DeliveryRecordDO deliveryRecordDO;

    public DeliInstManDO getDeliInstManDO() {
        return deliInstManDO;
    }

    public void setDeliInstManDO(DeliInstManDO deliInstManDO) {
        this.deliInstManDO = deliInstManDO;
    }

    public DeliveryRecordDO getDeliveryRecordDO() {
        return deliveryRecordDO;
    }

    public void setDeliveryRecordDO(DeliveryRecordDO deliveryRecordDO) {
        this.deliveryRecordDO = deliveryRecordDO;
    }
}
