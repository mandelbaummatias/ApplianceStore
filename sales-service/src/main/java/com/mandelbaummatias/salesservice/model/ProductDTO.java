package com.mandelbaummatias.salesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductDTO {
    private int id;
    private String name;
    private String brand;
    private double price;
}
