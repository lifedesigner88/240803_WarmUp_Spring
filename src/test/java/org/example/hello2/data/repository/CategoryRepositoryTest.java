package org.example.hello2.data.repository;

import com.google.gson.Gson;
import org.example.hello2.data.entity.Category;
import org.example.hello2.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Gson gson;

    @Test
    void relationsShipsTest() {
        Product product = Product.builder()
                .name("펜")
                .price(2000)
                .stock(100)
                .build();

        Product product2 = Product.builder()
                .name("펜2")
                .price(20002)
                .stock(1002)
                .build();

        productRepository.save(product);
        productRepository.save(product2);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProducts().add(product);
        category.getProducts().add(product2);

        categoryRepository.save(category);

        List<Product> products = categoryRepository.findById(1L).get().getProducts();


        System.out.println(
                gson.toJson(products)
        );

    }


}
