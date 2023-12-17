package com.mandelbaummatias.productsservice.repository;

import com.mandelbaummatias.productsservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT p FROM Product p WHERE p.id = :id")
    List<Product> getProductsById(@Param("id") int id);
}
