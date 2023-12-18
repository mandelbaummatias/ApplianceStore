package com.mandelbaummatias.salesservice.service;

import com.mandelbaummatias.salesservice.entity.Sale;
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
//        double total = 0;
//        for (Integer shoppingCartId : sale.getShoppingCartsId()) {
//            total += shoppingCartAPIClient.getShoppingCartById(shoppingCartId).getPrice();
//        }
//
//        sale.setTotalAmount(total);
//
//        return saleRepository.save(sale);
        return new Sale();
    }

    @Override
    public ShoppingCartDTO getShoppingCartById(int id) {
        return shoppingCartAPIClient.getShoppingCartById(id);
    }

    @Override
    public double getTotalAmount(int id) {
        val cart = this.getShoppingCartById(id);
        if(cart != null){
            return cart.getTotalAmount();
        } else{
            return 0;
        }
    }


}
