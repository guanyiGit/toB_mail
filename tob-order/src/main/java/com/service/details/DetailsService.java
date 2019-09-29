package com.service.details;

import com.github.pagehelper.PageInfo;
import com.pojo.DeliveryDetailVO;
import com.pojo.InstallDetailVO;
import com.pojo.OtherCostInfo;
import com.utils.ReqPage;

public interface DetailsService {
    /**
     * 查看送貨列表
     *
     * @param orderId
     * @param reqPage
     * @return
     */
    PageInfo<DeliveryDetailVO> findDeliveryDetailsByOrderId(long orderId, ReqPage reqPage);

    /**
     * 查看安装列表
     *
     * @param orderId
     * @param reqPage
     * @return
     */
    PageInfo<InstallDetailVO> findInstallDetailsByOrderId(long orderId, ReqPage reqPage);
    /**
     * 查看费用列表
     * @param orderId
     * @param reqPage
     * @return 费用类型，费用
     *          0其他费用,
     *          1安装费,
     *          2运费,
     *          3耗材费,
     *          4高空作业费
     */
    PageInfo<OtherCostInfo> findOtherCostDetailsByOrderId(long orderId, ReqPage reqPage);
}
