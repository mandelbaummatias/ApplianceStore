package com.mandelbaummatias.shoppingcartsservice.repository;

import com.mandelbaummatias.shoppingcartsservice.model.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductAPIClient {

    @GetMapping("products/{id}")
    public ProductDTO getProductById(@PathVariable("id") int id);
}
