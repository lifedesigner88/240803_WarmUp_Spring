package org.example.hello2.data.dao;


import org.example.hello2.data.entity.Product;

public interface ProductDAO {

    Product insertProduct(Product product);
    Product selectProduct(Long number);
    Product updateProductName(Long number, String name) throws Exception;
    void deleteProduct(Long number) throws Exception;

}
