package com.Retrozone.retrozone_bd.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

    @NotNull(message = "El id de usuario es obligatorio")
    private Long userId;

    @NotNull(message = "El id de producto es obligatorio")
    private Long productId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    private Integer quantity;
}