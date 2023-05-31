package com.luxoft.demoshopwithspring.repository;

import com.luxoft.demoshopwithspring.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndBrandAndQuality(String name, String brand, String quality);
}
