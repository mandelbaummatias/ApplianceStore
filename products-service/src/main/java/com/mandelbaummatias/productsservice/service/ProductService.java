package com.mandelbaummatias.productsservice.service;

import com.mandelbaummatias.productsservice.model.Product;
import com.mandelbaummatias.productsservice.repository.ProductRepository;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(int id);

    Product createProduct(Product product);

}
