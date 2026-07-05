package com.Retrozone.retrozone_bd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    // Relación con Usuarios: Muchos carritos pertenecen a un usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // Relación con Productos: Muchos registros de carrito apuntan a un producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(name = "cart_unit_price")
    private BigDecimal cartUnitPrice;

    @NotNull
    @Min(1)
    @Column(name = "cart_product_quantity")
    private Integer cartProductQuantity;

    @NotNull
    @Column(name = "cart_total_price")
    private BigDecimal cartTotalPrice;
}