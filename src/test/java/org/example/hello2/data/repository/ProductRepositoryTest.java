package org.example.hello2.data.repository;

import org.example.hello2.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save() {

        // given
        Product product = new Product();

        product.setName("펜2");
        product.setPrice(1000);
        product.setStock(100);

        Product savedProduct = productRepository.save(product);

        System.out.println("✅ " + savedProduct);

        Product findProduct = productRepository.findByName("펜2").get(0);

        System.out.println("🐦‍🔥" + findProduct);

        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());


    }

}
