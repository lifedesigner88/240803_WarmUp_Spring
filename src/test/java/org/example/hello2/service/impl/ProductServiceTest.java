package org.example.hello2.service.impl;

import org.example.hello2.data.dto.ProductDto;
import org.example.hello2.data.dto.ProductResponseDto;
import org.example.hello2.data.entity.Product;
import org.example.hello2.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    private ProductServiceImpleForTest productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpleForTest(productRepository);
    }

    @Test
    void getProductTest() {
        Product givenProduct = Product.builder()
                .number(123L)
                .name("펜")
                .price(1000)
                .stock(1234)
                .build();

        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto =
                productService.getProduct(123L);

        Assertions.assertEquals(
                productResponseDto.getNumber(),
                givenProduct.getNumber());
        Assertions.assertEquals(
                productResponseDto.getName(),
                givenProduct.getName());
        Assertions.assertEquals(
                productResponseDto.getPrice(),
                givenProduct.getPrice());
        Assertions.assertEquals(
                productResponseDto.getStock(),
                givenProduct.getStock());

        verify(productRepository).findById(123L);

    }


    @Test
    void saveProductTest() {

        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());
        ProductResponseDto productResponseDto =
                productService.saveProduct(new ProductDto("펜", 1000, 1234));

        Assertions.assertEquals(productResponseDto.getName(), "펜");
        Assertions.assertEquals(productResponseDto.getPrice(), 1000);
        Assertions.assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }

}
