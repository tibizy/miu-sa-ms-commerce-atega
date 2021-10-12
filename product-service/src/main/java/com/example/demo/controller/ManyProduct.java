package com.example.demo.controller;

import com.example.demo.dto.ProductOrder;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import lombok.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@AllArgsConstructor

@Data
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ManyProduct {

    private List<ProductOrder> productOrders;



}
