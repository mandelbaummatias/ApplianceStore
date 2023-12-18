package com.mandelbaummatias.salesservice.repository;

import com.mandelbaummatias.salesservice.model.ProductDTO;
import com.mandelbaummatias.salesservice.model.ShoppingCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "shoppingcarts-service")
public interface ShoppingCartAPIClient {

    @GetMapping("/shoppingCarts/{id}")
    public ShoppingCartDTO getShoppingCartById(@PathVariable("id") int id);

    @GetMapping("/shoppingCarts/total/{id}")
    public double getTotalAmountById(@PathVariable("id") int id);

    @GetMapping("/shoppingCarts/getAllProducts/{id}")
    public List<ProductDTO> getAllProductsFromCart(@PathVariable("id") int id);
}
