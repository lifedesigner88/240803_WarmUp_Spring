package org.example.hello2.data.repository.support;

import org.example.hello2.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepositorySupport")
public interface ProductRepository extends ProductRepositoryCustom , JpaRepository<Product, Long> {



}
