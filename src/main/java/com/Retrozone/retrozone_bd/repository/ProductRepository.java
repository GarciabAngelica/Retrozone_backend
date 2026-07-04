package com.Retrozone.retrozone_bd.repository;

import com.Retrozone.retrozone_bd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
