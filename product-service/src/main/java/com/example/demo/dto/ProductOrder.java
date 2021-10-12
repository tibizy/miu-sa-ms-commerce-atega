package com.example.demo.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {
    private long productId;
    private int quantity;
}
