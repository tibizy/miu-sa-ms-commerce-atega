package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//
//@Entity
//public class Product {
//
//    //public static int getQuantity;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long productId;
//    private String name;
//    private String vendor;
//    private String category;
//    private int quantity;
//    public void setQuantity(int quantity) {
//        this.quantity=quantity;
//    }
//     public int getQuantity() {
//        return quantity;
//    //private int quantity;
//
//   // public int getQuantity() {
//      //  return quantity;
//   // }
//
//   // public void setQuantity(int quantity) {
//        //this.quantity = quantity;
//    }
//}
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "seq_product", sequenceName = "seq_product", allocationSize = 1)
public class Product{

    @Id
    @GeneratedValue(generator = "seq_product", strategy = GenerationType.SEQUENCE)
    private Long productId;
    private String name;
    private String vendor;
    private Long quantity;
    private Double amount;
    private String category;
     }