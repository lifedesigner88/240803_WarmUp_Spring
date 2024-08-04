package org.example.hello2.service.impl;

import org.example.hello2.data.dto.ProductDto;
import org.example.hello2.data.dto.ProductResponseDto;
import org.example.hello2.service.ProductService;

public class ProductServiceImpleForTest implements ProductService {

    @Override
    public ProductResponseDto getProduct(Long number) {
        return null;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String newName) throws Exception {
        return null;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

    }
}
