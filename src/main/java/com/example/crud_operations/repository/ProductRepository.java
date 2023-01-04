package com.example.crud_operations.repository;

import com.example.crud_operations.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String rice);

  //  void deleteById(Product id);

  //  Object saveAll();


//    Optional<Product> findByName();

}
