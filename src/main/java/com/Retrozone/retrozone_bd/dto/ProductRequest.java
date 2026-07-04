package com.Retrozone.retrozone_bd.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String status,
        String platform,
        String genre,
        Integer categoryId
) {
}
