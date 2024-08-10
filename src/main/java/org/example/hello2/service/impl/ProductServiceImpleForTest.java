package org.example.hello2.service.impl;

import org.example.hello2.data.dto.ProductDto;
import org.example.hello2.data.dto.ProductResponseDto;
import org.example.hello2.data.entity.Product;
import org.example.hello2.data.repository.ProductRepository;
import org.example.hello2.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpleForTest implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpleForTest.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpleForTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productRepository.findById(number).get();
        LOGGER.info("[getProduct] product number : {}, name : {}",
                product.getNumber(), product.getName());
        return new ProductResponseDto(product);
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        LOGGER.info("[saveProduct] productDTO : {}", productDto.toString());
        Product product = new Product(productDto);
        Product savedProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] savedProduct : {}", savedProduct);
        return new ProductResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String newName) throws Exception {
        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(newName);
        Product updatedProduct = productRepository.save(foundProduct);
        return new ProductResponseDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productRepository.deleteById(number);
    }
}
