package com.example.crud_operations.controller;


import com.example.crud_operations.entity.Product;
import com.example.crud_operations.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//The @RestController annotation tells the Spring Boot Application that HTTP requests are handled by this class.
@RestController
//@RequestMapping
//Simply put, the annotation is used to map web requests to Spring Controller methods
//mapping an HTTP request to a method using some basic criteria.
@RequestMapping("/api/crud")
public class ProductController {

    @Autowired
    private ProductService service;

    //Rest End points for all Methods  in Service

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product item ){
        return service.saveProduct(item);
    }
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> items ){
        return service.saveProducts(items);
    }

    //fetch product by id
    @GetMapping("/product/{id}")
    public Product findByProductId(@PathVariable int id){
        return service.getSingleProduct(id);
    }

// @RequestParam makes Spring to map request parameters from the GET/POST request to your method argument.
// @RequestBody makes Spring to map entire request to a model class and from there you can retrieve or set values
// from its getter and setter methods
    //@PathVariables extract values from the URI path:
    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return service.getProducts();
    }
//get product by name end points
    @GetMapping("/product")
    public Product findProductByName(@RequestParam String name){
        return service.getSingleProductByname(name);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product obj){
        return service.updateProduct(obj);
    }

    @DeleteMapping("/delete/{id}")
    public void  deleteProduct(@PathVariable("id") int  product){
        service.deleteProduct(product);
    }

}
