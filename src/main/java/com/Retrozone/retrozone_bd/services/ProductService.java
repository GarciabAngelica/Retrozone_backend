package com.Retrozone.retrozone_bd.services;

import com.Retrozone.retrozone_bd.dto.ProductRequest;
import com.Retrozone.retrozone_bd.model.Product;
import com.Retrozone.retrozone_bd.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // AGREGAR PRODUCTO
    public Product addProduct(ProductRequest productRequest) {

        Product newProduct = new Product();

        if (productRequest.name() != null) newProduct.setProductName(productRequest.name());
        if (productRequest.description() != null) newProduct.setProductDescription(productRequest.description());
        if (productRequest.price() != null) newProduct.setProductPrice(productRequest.price());
        if (productRequest.stock() != null) newProduct.setProductStock(productRequest.stock());
        if (productRequest.status() != null) newProduct.setProductStatus(productRequest.status());
        if (productRequest.platform() != null) newProduct.setProductPlatform(productRequest.platform());
        if (productRequest.genre() != null) newProduct.setProductGenre(productRequest.genre());
       // if (productRequest.categoryId() != null) newProduct.setCategoryCategoryId(productRequest.categoryId());

        productRepository.save(newProduct);
        return newProduct;
    }

    // OBTENER UN PRODUCTO POR ID
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Producto no encontrado")
        );
    }

    // ELIMINAR UN PRODUCTO
    public Product deleteProductById(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
        return product;
    }

    // OBTENER TODOS LOS PRODUCTOS
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
