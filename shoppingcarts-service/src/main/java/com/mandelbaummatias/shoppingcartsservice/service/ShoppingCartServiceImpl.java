package com.mandelbaummatias.shoppingcartsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mandelbaummatias.shoppingcartsservice.entity.ShoppingCart;
import com.mandelbaummatias.shoppingcartsservice.model.ProductDTO;
import com.mandelbaummatias.shoppingcartsservice.repository.ProductAPIClient;
import com.mandelbaummatias.shoppingcartsservice.repository.ShoppingCartRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        double total = 0;
        for (Integer productId : shoppingCart.getProductsId()) {
            total += productAPIClient.getProductById(productId).getPrice();
        }

        shoppingCart.setTotalAmount(total);

        return shoppingCartRepository.save(shoppingCart);
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
