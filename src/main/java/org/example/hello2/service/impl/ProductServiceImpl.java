package org.example.hello2.service.impl;

import org.example.hello2.data.dao.ProductDAO;
import org.example.hello2.data.dto.ProductDto;
import org.example.hello2.data.dto.ProductResponseDto;
import org.example.hello2.data.entity.Product;
import org.example.hello2.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {

        Product product = productDAO.selectProduct(number);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

        Product product = new Product(productDto);
        Product savedProduct = productDAO.insertProduct(product);
        return new ProductResponseDto(savedProduct);

    }

    @Override
    public ProductResponseDto changeProductName(Long number, String newName) throws Exception {
        Product changedProduct = productDAO.updateProductName(number, newName);
        return new ProductResponseDto(changedProduct);
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        productDAO.deleteProduct(number);


    }
}

