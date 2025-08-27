package com.example.geh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.geh.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}