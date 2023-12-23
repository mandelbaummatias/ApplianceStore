package com.mandelbaummatias.shoppingcartsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mandelbaummatias.shoppingcartsservice.entity.ShoppingCart;
import com.mandelbaummatias.shoppingcartsservice.model.ProductDTO;
import com.mandelbaummatias.shoppingcartsservice.repository.ProductAPIClient;
import com.mandelbaummatias.shoppingcartsservice.repository.ShoppingCartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductAPIClient productAPIClient;

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart getShoppingCartById(int id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackCreateShoppingCart")
    @Retry(name = "products-service")
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        double total = 0;
        double price = 0;
        for (Integer productId : shoppingCart.getProductsId()) {
            price = productAPIClient.getProductById(productId).getPrice();
            total += price;
            System.out.println("price " + price);
        }

        shoppingCart.setTotalAmount(total);

      //  createException();

        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart fallbackCreateShoppingCart(Throwable throwable) {
        return new ShoppingCart(9999, 0, Collections.emptyList());
    }

    public void createException() {
        throw new IllegalArgumentException("Error in creating cart");
    }

    @Override
    public ShoppingCart addProductsToShoppingCart(int id, List<Integer> products) {
        ShoppingCart shoppingCart = getShoppingCartById(id);
        double total = 0;
        for (int product : products) {
            ProductDTO productDTO = productAPIClient.getProductById(product);
            total += productDTO.getPrice();
        }

        shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() + total);
        shoppingCart.getProductsId().addAll(products);

        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public ShoppingCart deleteProductsFromShoppingCart(int id, List<Integer> products) {
        ShoppingCart shoppingCart = getShoppingCartById(id);
        double total = 0;
        for (int product : products) {
            ProductDTO productDTO = productAPIClient.getProductById(product);
            total += productDTO.getPrice();
        }

        shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() - total);
        shoppingCart.getProductsId().removeAll(products);

        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public List<ProductDTO> getProductsListById(int id) {
        val shoppingCart = getShoppingCartById(id);
        val listProducts = new ArrayList<ProductDTO>();
        if (shoppingCart != null) {
            for (int product : shoppingCart.getProductsId()) {
                val productRemote = this.productAPIClient.getProductById(product);
                listProducts.add(productRemote);
            }
            return listProducts;
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public double getTotalProductsAmount(int id) {
        ShoppingCart shoppingCart = getShoppingCartById(id);
        double total = 0;
        for (int product : shoppingCart.getProductsId()) {
            //  System.out.println(product);
            ProductDTO productDTO = productAPIClient.getProductById(product);
            total += productDTO.getPrice();
        }

        return total;
    }

}
