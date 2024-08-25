package org.example.hello2.data.repository;

import org.example.hello2.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository extends JpaRepository<Product, Long>
, QuerydslPredicateExecutor<Product> {
}
