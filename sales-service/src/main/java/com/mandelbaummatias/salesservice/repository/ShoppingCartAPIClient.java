package com.mandelbaummatias.salesservice.repository;

import com.mandelbaummatias.salesservice.model.ShoppingCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shoppingcarts-service")
public interface ShoppingCartAPIClient {

    @GetMapping("shoppingcarts/{id}")
    public ShoppingCartDTO getShoppingCartById(@PathVariable("id") int id);
}
