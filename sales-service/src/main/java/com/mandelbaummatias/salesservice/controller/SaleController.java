package com.mandelbaummatias.salesservice.controller;

import com.mandelbaummatias.salesservice.entity.Sale;
import com.mandelbaummatias.salesservice.model.ShoppingCartDTO;
import com.mandelbaummatias.salesservice.service.SaleService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return sales.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(sales, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable int id) {
        Sale sale = saleService.getSaleById(id);
        if (sale != null) {
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSale(@RequestBody Sale sale) {
        try {
            Sale createdSale = saleService.createSale(sale);
            return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create the sale: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shoppingCart/{id}")
    public ResponseEntity<?> getShoppingCartById(@PathVariable int id) {
        ShoppingCartDTO shoppingCartDTO = saleService.getShoppingCartById(id);
        if (shoppingCartDTO != null) {
            return new ResponseEntity<>(shoppingCartDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/shoppingCart/total/{id}")
    public ResponseEntity<?> getTotalAmountById(@PathVariable int id) {
        ShoppingCartDTO shoppingCartDTO = saleService.getShoppingCartById(id);
        if (shoppingCartDTO != null) {
            val total = shoppingCartDTO.getTotalAmount();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/shoppingCart/getAllProducts/{id}")
    public ResponseEntity<?> getAllProductsFromCart(@PathVariable int id) {
        val products = saleService.getAllProductsFromCart(id);
        return products.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(products, HttpStatus.OK);
    }
}