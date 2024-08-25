package org.example.hello2.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.hello2.data.entity.Product;
import org.example.hello2.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTestForQueryDSL {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    void queryDslTest() {

        Product product1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        Product product2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(300)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        Product product3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);


        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> porductList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : porductList) {
            System.out.println("⭐" + product);
        }

    }

    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    void queryDslTest2() {

        QProduct qProduct = QProduct.product;

        List<Product> productList = queryFactory
                .selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("🚀" + product);
        }

        List<String> productListName = queryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String name : productListName) {
            System.out.println("🐟" + name);
        }

        List<Tuple> tuplesList = queryFactory
                .select(qProduct.name, qProduct.price, qProduct.stock)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple tuple : tuplesList) {
            System.out.println("🐦‍🔥" + tuple);
        }

    }


}
