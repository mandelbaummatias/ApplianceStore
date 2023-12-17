package com.mandelbaummatias.shoppingcartsservice.controller;

import com.mandelbaummatias.shoppingcartsservice.entity.ShoppingCart;
import com.mandelbaummatias.shoppingcartsservice.model.ProductDTO;
import com.mandelbaummatias.shoppingcartsservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllShoppingCarts();
        return shoppingCarts.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable int id) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(id);
        if (shoppingCart != null) {
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        try {
            ShoppingCart createdShoppingCart = shoppingCartService.createShoppingCart(shoppingCart);
            return new ResponseEntity<>(createdShoppingCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create the shoppingCart: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/test/{id}")
//    public ResponseEntity<List<ProductDTO>> getAll(@PathVariable int id) {
//        List<ProductDTO> shoppingCarts = shoppingCartService.getAllProductsByCartId(id);
//        return shoppingCarts.isEmpty()
//                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
//                : new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
//    }
}