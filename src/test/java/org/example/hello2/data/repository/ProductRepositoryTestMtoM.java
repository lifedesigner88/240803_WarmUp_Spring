package org.example.hello2.data.repository;

import com.google.gson.Gson;
import org.assertj.core.util.Lists;
import org.example.hello2.data.entity.Producer;
import org.example.hello2.data.entity.Product;
import org.example.hello2.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class ProductRepositoryTestMtoM {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    Gson gson;
    @Autowired
    private ProviderRepository providerRepository;


    @Test
    @Transactional
    void relationsShipsTest() {

        Product product1 = saveProduct("동글펜", 500, 1000);
        Product product2 = saveProduct("네모 공책", 100, 2000);
        Product product3 = saveProduct("지우개", 152, 1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");
        Producer producer3 = saveProducer("wikibooks33");

        producer1.addProduct(product1);
        producer1.addProduct(product2);
        producer2.addProduct(product2);
        producer2.addProduct(product3);

        product1.addProducer(producer3);
        product2.addProducer(producer1);
        product2.addProducer(producer2);
        product3.addProducer(producer2);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
        productRepository.saveAll(Lists.newArrayList(product1, product2, product3));

        System.out.println(
                gson.toJson(
                        producerRepository.findById(1L).get().getProducts())
        );

        System.out.println(
                gson.toJson(
                        productRepository.findById(2L).get().getProducers())
        );
    }

    private Product saveProduct(String name, Integer price, Integer stock) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
        return productRepository.save(product);
    }

    private Producer saveProducer(String name) {
        Producer producer = new Producer();
        producer.setName(name);
        return producerRepository.save(producer);
    }




    @Test
    @Transactional
    void cascadeTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000, 1000);
        Product product2 = savedProduct("상품2", 500, 1500);
        Product product3 = savedProduct("상품3", 750, 500);


        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);

        System.out.println("✅✅✅");
        providerRepository.findAll().forEach(System.out::println);
        System.out.println("✅");
        productRepository.findAll().forEach(System.out::println);

        System.out.println("✅✅✅✅✅✅");

        Provider foundProdvider = providerRepository.findById(1L).get();
        foundProdvider.getProductList().remove(0);

        System.out.println("✅✅✅");
        providerRepository.findAll().forEach(System.out::println);
        System.out.println("✅");
        productRepository.findAll().forEach(System.out::println);

    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        return Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }

}
