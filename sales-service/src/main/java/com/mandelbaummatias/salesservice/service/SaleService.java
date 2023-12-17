package com.mandelbaummatias.salesservice.service;



import com.mandelbaummatias.salesservice.entity.Sale;
import com.mandelbaummatias.salesservice.model.ShoppingCartDTO;

import java.util.List;

public interface SaleService {

    List<Sale> getAllSales();

    Sale getSaleById(int id);

    Sale createSale(Sale product);

    ShoppingCartDTO getShoppingCartById(int id);


}
