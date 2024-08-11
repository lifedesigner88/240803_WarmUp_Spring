package org.example.hello2.data.repository;

import org.example.hello2.data.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name, Sort sort);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.name = :name")
    void deleteByName(String name);

}
