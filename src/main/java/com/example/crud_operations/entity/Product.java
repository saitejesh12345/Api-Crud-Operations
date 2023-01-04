package com.example.crud_operations.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //to create an Object in Producttest easily
@Entity
@Table(name = "product")

public class Product {
    //The @Id annotation is inherited from javax.persistence.Id，
    // indicating the member field below is the primary key of current entity.
    // Hence your Hibernate and spring framework as well as you can do some reflect works based on this annotation.
    // for details please check javadoc for Id
    //The @GeneratedValue annotation is to configure the way of increment of the specified column(field).
    // For example when using Mysql, you may specify auto_increment in the definition of table to make it
    // self-incremental, and then use
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "quantity in KG")
    private int quantity;
    @Column(name = "ruppes")
    private double price;


    public Product(String name, int quantity, double price) {
      //  this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

//    public boolean isPresent() {
//        return false;
//    }
//
//    public int size() {
//        return 0;
//    }
}
