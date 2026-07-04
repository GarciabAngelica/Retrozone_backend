package com.Retrozone.retrozone_bd.services;

import com.Retrozone.retrozone_bd.dto.ProductRequest;
import com.Retrozone.retrozone_bd.model.Product;
import com.Retrozone.retrozone_bd.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    // 1. Inyección por constructor usando 'private final' como en image_5aea83.png
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // AGREGAR PRODUCTO (Equivalente a addSong)
    public Product addProduct(ProductRequest productRequest) {
        // Nota: En el futuro, si validan que la categoría exista, aquí buscarían en el categoryRepository
        // igual que el ejemplo busca el album con albumsRepository.findById(...).orElseThrow(...)

        Product newProduct = new Product();

        // Mapeo defensivo validando nulos tal como en tu ejemplo
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

    // OBTENER UN PRODUCTO POR ID (Equivalente a getSong)
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Producto no encontrado")
        );
    }

    // ELIMINAR UN PRODUCTO (Equivalente a deleteSongsById)
    public Product deleteProductById(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
        return product;
    }

    // OBTENER TODOS LOS PRODUCTOS (Equivalente a getAllSongs)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
