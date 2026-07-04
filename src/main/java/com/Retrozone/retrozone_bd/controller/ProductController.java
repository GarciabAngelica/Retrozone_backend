package com.Retrozone.retrozone_bd.controller;

import com.Retrozone.retrozone_bd.dto.ProductRequest;
import com.Retrozone.retrozone_bd.model.Product;
import com.Retrozone.retrozone_bd.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping // http://localhost:8080/api/products
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "{productId}") // http://localhost:8080/api/products/id
    public Product getProductsById(@PathVariable("productId") Long id) {
        return productService.getProduct(id);
    }

    @PostMapping // http://localhost:8080/api/products metodo POST
    public Product addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @DeleteMapping(path = "{productId}") // http://localhost:8080/api/products/id metodo DELETE
    public Product deleteProductById(@PathVariable("productId") Long id) {
        return productService.deleteProductById(id);
    }
}
