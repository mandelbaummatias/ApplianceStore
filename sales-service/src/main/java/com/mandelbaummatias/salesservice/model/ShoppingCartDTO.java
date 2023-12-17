package com.mandelbaummatias.salesservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ShoppingCartDTO {
    private int id;
    private double totalAmount;
   // @ElementCollection(fetch = FetchType.EAGER)
   // @JsonFormat
    private List<Integer> productsId;
}
