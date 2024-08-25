package org.example.hello2.data.repository;


import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.example.hello2.data.entity.Product;
import org.example.hello2.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class productRepositoryTest2 {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Gson gson;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationsShipsTest1() {
        Provider provider = new Provider();
        provider.setName("삼정물산");

        providerRepository.save(provider);

        Product product = Product.builder()
                .name("가위")
                .price(5000)
                .stock(500)
                .provider(provider)
                .build();

        productRepository.save(product);

        Product product2 = Product.builder()
                .name("딱풀")
                .price(5000)
                .stock(500)
                .provider(provider)
                .build();

        productRepository.save(product2);

        System.out.println("🐤 Product : " +
                gson.toJson(
                        productRepository.findById(1L)
                                .orElseThrow(RuntimeException::new)
                )
        );
        System.out.println("🐤 Provider : " +
                gson.toJson(
                        productRepository.findById(1L)
                                .orElseThrow(RuntimeException::new)
                                .getProvider()
                )
        );


        List<Product> products = providerRepository
                .findById(provider.getId()).get()
                .getProductList();

        System.out.println(gson.toJson(products));

    }


    @Test
    public void basicCRUDTest() {


        /* create */
        // given
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();

        // when
        Product savedProduct = productRepository.save(givenProduct);

        // then
        Assertions.assertThat(savedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(savedProduct.getName())
                .isEqualTo(givenProduct.getName());
        Assertions.assertThat(savedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(savedProduct.getStock())
                .isEqualTo(givenProduct.getStock());


        /* read */
        // when
        Product selectedProduct = productRepository.findById(
                        savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        // then
        Assertions.assertThat(selectedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(selectedProduct.getName())
                .isEqualTo(givenProduct.getName());
        Assertions.assertThat(selectedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(selectedProduct.getStock())
                .isEqualTo(givenProduct.getStock());

        /* update */
        // when
        Product foundProduct = productRepository.findById(
                        selectedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        foundProduct.setName("장난감");

        Product updatedProduct = productRepository.save(foundProduct);

        // then
        assertEquals(updatedProduct.getName(), "장난감");

        /* delete */
        // when
        productRepository.delete(updatedProduct);

        // then
        assertFalse(productRepository.findById(
                selectedProduct.getNumber()).isPresent());

    }

}
