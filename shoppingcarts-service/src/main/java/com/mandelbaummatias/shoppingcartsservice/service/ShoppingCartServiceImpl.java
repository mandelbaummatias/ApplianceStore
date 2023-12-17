package com.mandelbaummatias.shoppingcartsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mandelbaummatias.shoppingcartsservice.entity.ShoppingCart;
import com.mandelbaummatias.shoppingcartsservice.model.ProductDTO;
import com.mandelbaummatias.shoppingcartsservice.repository.ProductAPIClient;
import com.mandelbaummatias.shoppingcartsservice.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ObjectMapper objectMapper;

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
       // double total = getTotalProductsAmount(shoppingCart.getId());
//       // shoppingCart.setProductsId(list);
//        List list = new ArrayList<>();
//                list.add(1);
//                shoppingCart.setProductsId(list);
       // System.out.println(total);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public double getTotalProductsAmount(int id) {
        ShoppingCart shoppingCart = getShoppingCartById(id);
        double total = 0;
        for(int product: shoppingCart.getProductsId()){
            System.out.println(product);
            ProductDTO productDTO = productAPIClient.getProductById(product);
            total+= productDTO.getPrice();
        }

        return total;
       // return productAPIClient.getProductsById(id);
    }

    @Override
    public double getTotal() {
        return 0;
    }
}
