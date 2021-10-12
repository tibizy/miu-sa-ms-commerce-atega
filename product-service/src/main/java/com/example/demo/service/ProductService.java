package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll(){
        return  productRepository.findAll();
    }
    public Optional<Product> findById(Long productId){
        return productRepository.findById(productId);
    }
    public Product save(Product stock){
        return productRepository.save(stock);
    }
    public void deleteById(Long productId){
         productRepository.deleteById(productId);
    }
    public ProductDTO addMore(ProductDTO productDTO) {
    Product product = productRepository.save( productDTO.toEntity() );
    return ProductDTO.of( product );}

    public ProductDTO update(Long productId, ProductDTO productDTO) {Product product = productDTO.toEntity();
        product.setProductId( productId );
        Product updatedProduct = productRepository.save(product);
        return ProductDTO.of( updatedProduct );
    }
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    public void addProductQuantity(Product product){
        product.setQuantity(product.getQuantity() + 50);
        productRepository.save(product);
    }
    public void reduce(long productId, int quantity) {
        var product = findById(productId);
        if (product.isPresent()) {
            var curProduct = product.get();
            if (curProduct.getQuantity() - quantity < 0) {
                addProductQuantity(curProduct);
            }
            curProduct.setQuantity(curProduct.getQuantity() - quantity);
            productRepository.save(curProduct);
        }
    }
    public ProductDTO findOne(Long productId) {
        return productRepository.findById( productId )
                .map( ProductDTO::of )
                .orElse( null );
    }
    public List<ProductDTO> findByName(String name) {
        List<Product> products = productRepository.findByName( name );
        return products.stream()
                .map( ProductDTO::of )
                .collect( Collectors.toList() );
    }}


