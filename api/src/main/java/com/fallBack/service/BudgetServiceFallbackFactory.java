package com.fallBack.service;

import com.feign.service.BudgetService;
import com.utils.R;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class BudgetServiceFallbackFactory implements FallbackFactory<BudgetService> {

    private static final String FALLBACK_MEG = "fallback method message!";


    @Override
    public BudgetService create(Throwable throwable) {
        return new BudgetService() {
            @Override
            public R budgetPublic(Integer orgId) {
                return R.error(FALLBACK_MEG);
            }
        };
    }
}
