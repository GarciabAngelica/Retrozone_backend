package com.Retrozone.retrozone_bd.controller;

import com.Retrozone.retrozone_bd.dto.CartDTO;
import com.Retrozone.retrozone_bd.model.Cart;
import com.Retrozone.retrozone_bd.services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Para agregar productos al carrito
    @PostMapping("/add")
    public ResponseEntity<Cart> addProduct(@Valid @RequestBody CartDTO cartDTO) {
        Cart savedCart = cartService.addProductToCart(cartDTO);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    // Para ver el carrito de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartByUser(@PathVariable Long userId) {
        List<Cart> userCart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(userCart);
    }
}