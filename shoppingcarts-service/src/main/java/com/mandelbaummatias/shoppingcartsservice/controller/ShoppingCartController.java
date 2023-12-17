package com.mandelbaummatias.shoppingcartsservice.controller;

import com.mandelbaummatias.shoppingcartsservice.entity.ShoppingCart;
import com.mandelbaummatias.shoppingcartsservice.model.ProductDTO;
import com.mandelbaummatias.shoppingcartsservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Value("${server.port}")
    private int serverPort;

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
        System.out.println("At port:" + serverPort);
        try {
            ShoppingCart createdShoppingCart = shoppingCartService.createShoppingCart(shoppingCart);
            return new ResponseEntity<>(createdShoppingCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create the shoppingCart: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/addProducts")
    public ResponseEntity<?> addProductsToCart(@PathVariable int id, @RequestBody List<Integer> productIds) {
        try {
            ShoppingCart modifiedShoppingCart = shoppingCartService.addProductsToShoppingCart(id, productIds);
            return new ResponseEntity<>(modifiedShoppingCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to modify the shoppingCart: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/removeProducts")
    public ResponseEntity<?> removeProductsFromCart(@PathVariable int id, @RequestBody List<Integer> productIds) {
        try {
            ShoppingCart modifiedShoppingCart = shoppingCartService.deleteProductsFromShoppingCart(id,productIds);
            return new ResponseEntity<>(modifiedShoppingCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete products from the shoppingCart: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


//    @GetMapping("/test/{id}")
//    public ResponseEntity<Double> getAll(@PathVariable int id) {
//        double total = shoppingCartService.getTotalProductsAmount(id);
//        return total == 0
//                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
//                : new ResponseEntity<>(total, HttpStatus.OK);
//    }
}