package com.Retrozone.retrozone_bd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank
    private String productName;

    @NotBlank
    private String productDescription;

    @NotBlank
    private BigDecimal productPrice;

    @NotBlank
    private Integer productStock;

    @NotBlank
    private String productStatus;

    @NotBlank
    private String productPlatform;

    @NotBlank
    private String productGenre;

    //@OneToMany(mappedBy = "Category", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Integer categoryId;





}
