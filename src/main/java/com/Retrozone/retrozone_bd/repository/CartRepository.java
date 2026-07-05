package com.Retrozone.retrozone_bd.repository;

import com.Retrozone.retrozone_bd.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser_Id(Long userId);
}