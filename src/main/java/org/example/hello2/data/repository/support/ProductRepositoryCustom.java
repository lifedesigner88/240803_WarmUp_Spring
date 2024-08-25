package org.example.hello2.data.repository.support;

import org.example.hello2.data.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findByName(String name);

}
