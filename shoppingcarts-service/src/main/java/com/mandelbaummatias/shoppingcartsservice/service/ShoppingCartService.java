package com.mandelbaummatias.shoppingcartsservice.service;

import com.mandelbaummatias.shoppingcartsservice.entity.ShoppingCart;
import com.mandelbaummatias.shoppingcartsservice.model.ProductDTO;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> getAllShoppingCarts();

    ShoppingCart getShoppingCartById(int id);

    ShoppingCart createShoppingCart(ShoppingCart product);

    double getTotalProductsAmount(int id);

    ShoppingCart addProductsToShoppingCart(int id, List<Integer> products);

    ShoppingCart deleteProductsFromShoppingCart(int id, List<Integer> products);

    List<ProductDTO> getProductsListById(int id);

}
