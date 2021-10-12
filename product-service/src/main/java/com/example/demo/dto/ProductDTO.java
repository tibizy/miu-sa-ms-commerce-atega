package com.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.demo.entity.Product;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String vendor;
    private Long quantity;
    private Double amount;
    private String category;

    public Product toEntity() {
        return new Product( id, name, vendor, quantity, amount , category);
    }

    public static ProductDTO of(Product product) {
        return new ProductDTO( product.getProductId(), product.getName(), product.getVendor(),
                product.getQuantity(), product.getAmount(), product.getCategory());
    }
}

