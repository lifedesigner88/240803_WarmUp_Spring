package org.example.hello2.data.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.hello2.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;



@SpringBootTest
public class ProductRepositoryTest3 {

    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest() {

        Product product1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();

        Product product2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(300)
                .build();

        Product product3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .build();

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        List<Product> list1 =  productRepository.findByName("펜", Sort.by(Order.asc("price")));
        List<Product> list2 =  productRepository.findByName("펜", getSort() );


        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        System.out.println("🚀" + gson.toJson(list1));
        System.out.println("🚀" + gson.toJson(list2));


        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));

        System.out.println("✅" + gson.toJson(productPage));
        System.out.println("✅❤️❤️" + gson.toJson(productPage.getContent()));

        productRepository.deleteByName("펜");

    }



    @Test
    void getTest() {

        Product product1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();

        Product product2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(300)
                .build();

        Product product3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> list = productRepository.findByName("펜");

        System.out.println("✅✅" + toJson(Collections.singletonList(list)));


        List<Object[]> selectedList = productRepository.findByNameForNativeQuery("펜");

        System.out.println("❤️❤️❤️" + toJson(Collections.singletonList(selectedList)));




        productRepository.deleteByName("펜");
    }

    private String toJson(List<Object> list) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        return gson.toJson(list);

    }


    private Sort getSort() {
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }



}

class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void write(JsonWriter writer, LocalDateTime localDateTime) throws IOException {
        writer.value(localDateTime.format(formatter));
    }

    @Override
    public LocalDateTime read(JsonReader reader) throws IOException {
        return LocalDateTime.parse(reader.nextString(), formatter);
    }
}
