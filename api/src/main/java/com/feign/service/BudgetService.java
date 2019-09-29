package com.feign.service;

import com.fallBack.service.BudgetServiceFallbackFactory;
import com.utils.R;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "TOB-BUDGET", fallbackFactory = BudgetServiceFallbackFactory.class)
@RequestMapping(value = "/biz/budget")
public interface BudgetService {

    /**
     * 封装公共的预算使用接口
     *
     * @param orgId 登录人组织id
     * @return
     */
    @RequestMapping("/budgetPublic")
    R budgetPublic(Integer orgId);
}
