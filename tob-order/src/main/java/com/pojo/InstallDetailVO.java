package com.pojo;

import com.entities.DeliInstManDO;
import com.entities.InstallDetailDO;
import com.entities.InstallRecordDO;

public class InstallDetailVO extends InstallDetailDO {

    private InstallRecordDO installRecordDO;

    private DeliInstManDO deliInstManDO;

    public InstallRecordDO getInstallRecordDO() {
        return installRecordDO;
    }

    public void setInstallRecordDO(InstallRecordDO installRecordDO) {
        this.installRecordDO = installRecordDO;
    }

    public DeliInstManDO getDeliInstManDO() {
        return deliInstManDO;
    }

    public void setDeliInstManDO(DeliInstManDO deliInstManDO) {
        this.deliInstManDO = deliInstManDO;
    }
}
