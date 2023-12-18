package com.mandelbaummatias.salesservice.service;

import com.mandelbaummatias.salesservice.entity.Sale;
import com.mandelbaummatias.salesservice.model.ProductDTO;
import com.mandelbaummatias.salesservice.model.ShoppingCartDTO;
import com.mandelbaummatias.salesservice.repository.ShoppingCartAPIClient;
import com.mandelbaummatias.salesservice.repository.SaleRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ShoppingCartAPIClient shoppingCartAPIClient;

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(int id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public ShoppingCartDTO getShoppingCartById(int id) {
        val sale = this.getSaleById(id);
        return shoppingCartAPIClient.getShoppingCartById(sale.getCartId());
    }

    @Override
    public double getTotalAmount(int id) {
        val sale = this.getSaleById(id);
        val cart = this.getShoppingCartById(sale.getCartId());
        if (cart != null) {
            return cart.getTotalAmount();
        } else {
            return 0;
        }
    }

    @Override
    public List<ProductDTO> getAllProductsFromCart(int id) {
        val sale = this.getSaleById(id);
        return shoppingCartAPIClient.getAllProductsFromCart(sale.getCartId());
    }


}
