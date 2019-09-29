package com.controller.details;

import com.service.details.DetailsService;
import com.utils.R;
import com.utils.ReqPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order/details")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    /**
     * 查看送貨列表
     * @param orderId
     * @param reqPage
     * @return
     */
    @GetMapping(value = "/sh/{orderId}")
    public R sh(@PathVariable long orderId,  ReqPage reqPage) {
        try {
            return R.ok(detailsService.findDeliveryDetailsByOrderId(orderId,reqPage));
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }


    /**
     * 查看安装列表
     * @param orderId
     * @param reqPage
     * @return
     */
    @GetMapping(value = "/az/{orderId}")
    public R az(@PathVariable long orderId,  ReqPage reqPage) {
        try {
            return R.ok(detailsService.findInstallDetailsByOrderId(orderId,reqPage));
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

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
    @GetMapping(value = "/fy/{orderId}")
    public R fy(@PathVariable long orderId,  ReqPage reqPage) {
        try {
            return R.ok(detailsService.findOtherCostDetailsByOrderId(orderId,reqPage));
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

}
