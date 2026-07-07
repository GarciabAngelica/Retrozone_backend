package com.Retrozone.retrozone_bd.repository;

import com.Retrozone.retrozone_bd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
