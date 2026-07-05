package com.Retrozone.retrozone_bd.services;

import com.Retrozone.retrozone_bd.dto.CartDTO;
import com.Retrozone.retrozone_bd.model.Cart;
import com.Retrozone.retrozone_bd.model.Product;
import com.Retrozone.retrozone_bd.model.Users;
import com.Retrozone.retrozone_bd.repository.CartRepository;
import com.Retrozone.retrozone_bd.repository.ProductRepository;
import com.Retrozone.retrozone_bd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository usersRepository;
    // Método para agregar un producto al carrito
    public Cart addProductToCart(CartDTO cartDTO) {
        // 1. Validar si el usuario existe usando tu UsersRepository
        Users user = usersRepository.findById(cartDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + cartDTO.getUserId()));

        // 2. Validar si el producto existe
        Product product = productRepository.findById(cartDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + cartDTO.getProductId()));

        // 3. Realizar los cálculos del precio
        BigDecimal unitPrice = product.getProductPrice();
        BigDecimal quantity = BigDecimal.valueOf(cartDTO.getQuantity());
        BigDecimal totalPrice = unitPrice.multiply(quantity);

        // 4. Mapear los datos del DTO a la Entidad (Model)
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setCartUnitPrice(unitPrice);
        cart.setCartProductQuantity(cartDTO.getQuantity());
        cart.setCartTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    // Método para obtener todos los productos del carrito de un usuario específico
    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUser_Id(userId);
    }
}