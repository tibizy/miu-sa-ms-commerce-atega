package com.djff.orders.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection="product")
public class ProductModel {
    @Id
    UUID productId;
    int quantity;
    double price;

    public ProductModel() {}
    public ProductModel(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
