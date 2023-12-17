package com.mandelbaummatias.shoppingcartsservice.repository;

import com.mandelbaummatias.shoppingcartsservice.entity.ShoppingCart;
import com.mandelbaummatias.shoppingcartsservice.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
