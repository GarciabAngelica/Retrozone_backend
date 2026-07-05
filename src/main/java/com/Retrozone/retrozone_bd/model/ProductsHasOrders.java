package com.Retrozone.retrozone_bd.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;


public class ProductsHasOrders implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "Products_id_product")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "orders_id_order")
    private Order order;

    // constructor vacío
    public ProductsHasOrders() {}

    public ProductsHasOrders(Product product, Order order) {
        this.product = product;
        this.order = order;
    }

    // getters y setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // claves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductsHasOrders)) return false;
        ProductsHasOrders that = (ProductsHasOrders) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order);
    }
}
