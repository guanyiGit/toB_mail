package com.service.details.impl;

import com.dao.details.TOtherFeeMapper;
import com.entities.OrderDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ordermanagement.dao.OrderDao;
import com.pojo.CostDetail;
import com.pojo.DeliveryDetailVO;
import com.pojo.InstallDetailVO;
import com.pojo.OtherCostInfo;
import com.service.details.DetailsService;
import com.utils.ReqPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DetailsServiceImpl implements DetailsService {


    @Autowired
    private TOtherFeeMapper tOtherFeeMapper;

    @Autowired
    private OrderDao orderDao;

    /**
     * 根据订单查询其订单和子订单号
     *
     * @param orderId
     * @param orderId 自否包含自己
     * @return
     */
    private List<Long> findChildrenOrderIds(long orderId, boolean isOneself) {
        List<OrderDO> list = orderDao.findOrderDetailsById(orderId, isOneself);
        if (null != list && list.size() > 0) {
            return list.stream()
                    .filter(x -> x != null && x.getId() != null)
                    .map(OrderDO::getId)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public PageInfo<DeliveryDetailVO> findDeliveryDetailsByOrderId(long orderId, ReqPage reqPage) {
        List<Long> orderIds = this.findChildrenOrderIds(orderId, true);
        if (orderIds != null && orderIds.size() > 0) {
            PageHelper.startPage(reqPage.getPageNum(), reqPage.getLimit());
            List<DeliveryDetailVO> page = orderDao.findDeliveryDetailsInOrderIds(orderIds);
            return new PageInfo<DeliveryDetailVO>(page);
        }
        throw new RuntimeException("参数有误！");
    }

    @Override
    public PageInfo<InstallDetailVO> findInstallDetailsByOrderId(long orderId, ReqPage reqPage) {
        List<Long> orderIds = this.findChildrenOrderIds(orderId, true);
        if (orderIds != null && orderIds.size() > 0) {
            PageHelper.startPage(reqPage.getPageNum(), reqPage.getLimit());
            List<InstallDetailVO> page = orderDao.findInstallDetailsByOrderId(orderIds);
            return new PageInfo<InstallDetailVO>(page);
        }
        throw new RuntimeException("参数有误！");
    }

    @Override
    public PageInfo<OtherCostInfo> findOtherCostDetailsByOrderId(long orderId, ReqPage reqPage) {
        List<Long> orderIds = this.findChildrenOrderIds(orderId, true);
        if(orderIds!= null && orderIds.size()>0){
            PageHelper.startPage(reqPage.getPageNum(), reqPage.getLimit());
            List<OtherCostInfo> list = orderDao.findOtherCostInfos(orderIds);
            if(list!= null && !list.isEmpty()){
                Set<CostDetail> costSet = this.classifiedStatistic(orderIds);
                list.stream()
                        .forEach(x->x.setCostDetails(costSet));
            }
            return new PageInfo<OtherCostInfo>(list);
        }throw new RuntimeException("参数有误！");
    }

    /**
     * 分类费用统计
     * @param orderIds
     * @return
     */
    private Set<CostDetail> classifiedStatistic(List<Long> orderIds){
        if (orderIds != null && orderIds.size() > 0) {
            HashSet<CostDetail> costSet = new HashSet<>();

            List<Map<String, Object>> page = orderDao.findOtherCost(1, orderIds);
            if (page != null && page.size() > 0) {
                page.stream()
                        .map(x -> {
                            CostDetail cost = new CostDetail();
                            cost.setCategory(1);
                            cost.setCostType(Integer.valueOf(x.get("type").toString()));
                            cost.setSum(new BigDecimal(x.get("sum").toString()));
                            return cost;
                        })
                        .forEach(x->costSet.add(x));
            }

            List<Map<String, Object>> page2 = orderDao.findOtherCost(2, orderIds);
            if (page2 != null && page2.size() > 0) {
                page2.stream()
                        .map(x -> {
                            CostDetail cost = new CostDetail();
                            cost.setCategory(2);
                            cost.setCostType(Integer.valueOf(x.get("type").toString()));
                            cost.setSum(new BigDecimal(x.get("sum").toString()));
                            return cost;
                        })
                        .forEach(x->costSet.add(x));
            }

            return costSet;
        }
        throw new RuntimeException("参数有误！");
    }
}
