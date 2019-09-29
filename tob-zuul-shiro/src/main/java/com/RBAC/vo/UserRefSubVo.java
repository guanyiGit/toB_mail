package com.RBAC.vo;



import com.RBAC.domain.UserDO;
import com.RBAC.domain.UserRefSubDO;

import java.util.List;

public class UserRefSubVo extends UserDO {

   private List<UserRefSubDO> userRefSubDO;

    public List<UserRefSubDO> getUserRefSubDO() {
        return userRefSubDO;
    }

    public void setUserRefSubDO(List<UserRefSubDO> userRefSubDO) {
        this.userRefSubDO = userRefSubDO;
    }
}
