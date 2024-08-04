package org.example.hello2.service;

import org.example.hello2.data.dto.ProductDto;
import org.example.hello2.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String newName) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
