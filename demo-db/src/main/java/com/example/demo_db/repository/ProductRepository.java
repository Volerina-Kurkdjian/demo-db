package com.example.demo_db.repository;

import com.example.demo_db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    Optional<Product> findByName(String name);
}
