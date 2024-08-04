package org.example.hello2.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private String name;
    private int price;
    private int stock;
}
