package com.fallBack.service;

import com.entities.CardDO;
import com.feign.service.CartService;
import com.utils.R;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CartServiceFallbackFactory implements FallbackFactory<CartService> {

    private static final String FALLBACK_MEG = "fallback method message!";

    @Override
    public CartService create(Throwable throwable) {
        return new CartService() {
            @Override
            public R add(CardDO cardDO) {
                return R.error(FALLBACK_MEG);
            }

            @Override
            public R account(long userId) {
                return R.error(FALLBACK_MEG);
            }
        };
    }
}
