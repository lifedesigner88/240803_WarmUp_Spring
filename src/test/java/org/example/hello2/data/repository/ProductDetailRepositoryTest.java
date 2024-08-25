package org.example.hello2.data.repository;

import com.google.gson.Gson;
import org.example.hello2.data.entity.Product;
import org.example.hello2.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Gson gson;

    @Test
    public void saveAndReadTest1() {
        Product product = Product.builder()
                .name("스프링 부트 JPA")
                .price(5000)
                .stock(500)
                .build();

        productRepository.save(product);

        ProductDetail productDetail = ProductDetail.builder()
                .product(product)
                .description("스프링 부트와 JPA를 함께 볼 수 있는 책")
                .build();

        productDetailRepository.save(productDetail);

        System.out.println("✅ savedProduct : " +

                gson.toJson(
                        productDetailRepository
                                .findById(productDetail.getId()).get()
                                .getProduct()
                )
        );
        System.out.println("✅ savedProductDetail : " +
                gson.toJson(
                        productDetailRepository
                                .findById(productDetail.getId()).get()
                )
        );


    }
}
