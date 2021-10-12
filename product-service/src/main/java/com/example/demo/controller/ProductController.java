package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor

public class ProductController {

    private  ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable Long productId) {
        int count = 0;
        Optional<Product> product = productService.findById(productId);
        //return ResponseEntity.ok(productService.findById(productId).get());
        if (product.isPresent()) {

            return new ResponseEntity(product, HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity creat(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping
    public ResponseEntity<String> updateMany(@RequestBody ManyProduct manyProduct){
        manyProduct.getProductOrders().forEach(
                productOrder -> productService.reduce(productOrder.getProductId(), productOrder.getQuantity())
        );
        return ResponseEntity.ok("Successfully updated products");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable Long productId, @RequestBody Product product) {
        return ResponseEntity.accepted().body(productService.save(product));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity delete(@PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.accepted().build();

    }
}


