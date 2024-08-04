package org.example.hello2.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hello2.data.dto.ProductDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Product(ProductDto productDto) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.stock = productDto.getStock();
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

}
