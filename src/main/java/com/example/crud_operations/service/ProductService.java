package com.example.crud_operations.service;


import com.example.crud_operations.entity.Product;
import com.example.crud_operations.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    //to save one product
    public Product saveProduct(Product item){

        return repository.save(item);
    }

    // to save List of products
    public List<Product> saveProducts(List<Product> items) {
        System.out.println("Getting data from DB : " + items  );
        return repository.saveAll(items);
    }
    // to fetch single product/item from database
    public Product getSingleProduct(int id){
        return repository.findById(id).
                orElse(null);
        }

    //to fetch list of all items from database
public List<Product> getProducts(){

        return repository.findAll();
}

//get method to fetch based on name
public Product getSingleProductByname(String name){
        return repository.findByName(name);
}

// to delete the product
//    public String deleteProduct(int id){
//        repository.deleteById(id);
//        return "Product Deleted Succesfully!!" + id;
//    }
//update the product
//    public Product updateProduct(Product obj){
//        Product existingProduct = repository.findById(obj.getId()).orElse(null);
//        existingProduct.setName(obj.getName());
//        existingProduct.setQuantity(obj.getQuantity());
//        existingProduct.setPrice(obj.getPrice());
//        return repository.save(existingProduct);
//    }


    //update product other code
public Product updateProduct(Product obj) {

        return repository.save(obj);
}

    public void deleteProduct(int product) {
        repository.deleteById(product);

    }


}
