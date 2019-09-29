package com.feign.service;

import com.entities.CardDO;
import com.fallBack.service.CartServiceFallbackFactory;
import com.utils.R;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "TOB-SHOPPING-CART", fallbackFactory = CartServiceFallbackFactory.class)
@RequestMapping(value = "/biz/card")
public interface CartService {

    @PostMapping
    R add(@RequestBody CardDO cardDO);

    @GetMapping("/account/{userId}")
    R account(@PathVariable(value = "userId") long userId);
}
