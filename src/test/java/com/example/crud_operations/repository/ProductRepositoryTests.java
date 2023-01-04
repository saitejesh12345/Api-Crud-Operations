package com.example.crud_operations.repository;


import com.example.crud_operations.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

private Product product;

    @BeforeEach
    public void setup() {
        product = Product.builder()
                .id(1)
                .name("Black grams")
                .quantity(4)
                .price(350)
                .build();
    }

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenProductObject_whenSave_thenReturnSavedProduct() {
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    // JUnit test for save all Product operation
    @DisplayName("JUnit test for save all products operation")
    @Test
    public void saveProductsTest() {
        List<Product> products = new ArrayList<Product>();
        Product product1 = Product.builder()
                .id(2)
                .name("orange grams")
                .quantity(4)
                .price(300)
                .build();
        products.add(product1);
        products.add(product);
        productRepository.saveAll(products);
        List<Product> studentList = productRepository.findAll();
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
    }


    // JUnit test for get all Product operation
    @DisplayName("JUnit test for get all products operation")
    @Test
    public void givenProductList_whenFindAll_thenProductList() {
        // given - precondition or setup


        Product product1 = product.builder()
                //.id(2)
                .name("purple grams")
                .quantity(4)
                .price(300)
                .build();

        productRepository .save(product);
        productRepository.save(product1);
        // when -  action or the behaviour that we are going test
      //  given(productRepository.findAll()).willReturn(Arrays.asList(product,product1));
        List<Product> ProductList = productRepository.findAll();
        // then - verify the output
        assertThat(ProductList).isNotNull();
        assertThat(ProductList.size()).isEqualTo(2);


    }
    // JUnit test for get product by id operation
    @DisplayName("JUnit test for get product by id operation")
    @Test
    public void givenProductObject_whenFindById_thenReturnProductObject() {
        // given - precondition or setup
        Product product1 = product.builder()
                //.id(2)
                .name("purple grams")
                .quantity(4)
                .price(300)
                .build();
        productRepository.save(product1);

        // when -  action or the behaviour that we are going test
      //  Product employeeDB = new Product();
       Product  employeeDB = productRepository.findById(product1.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
        //assertThat(employeeDB.size()).isEqualTo(1);
    }
    @DisplayName("JUnit test for update product operation")
    @Test
    public void givenProductObject_whenUpdateProduct_thenReturnUpdatedProduct() {
        // given - precondition or setup
        Product product1 = product.builder()
                //.id(2)
                .name("purple grams")
                .quantity(4)
                .price(300)
                .build();
        productRepository.save(product1);

        // when -  action or the behaviour that we are going test
        Product savedEmployee =  productRepository.findById(product1.getId()).get();
        savedEmployee.setName("white-powder");
        savedEmployee.setPrice(400);
        Product updatedEmployee =  productRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getName()).isEqualTo("white-powder");
        assertThat(updatedEmployee.getPrice()).isEqualTo(400);
    }
    // JUnit test for delete employee operation
    @DisplayName("JUnit test for delete product operation")
    @Test
    public void givenProductObject_whenDelete_thenRemoveProduct() {
        // given - precondition or setup
        Product product1 = product.builder()
                //.id(2)
                .name("purple grams")
                .quantity(4)
                .price(300)
                .build();
        productRepository.save(product1);

        // when -  action or the behaviour that we are going test
        productRepository.deleteById(product1.getId());
        Optional<Product> employeeOptional =  productRepository.findById(product1.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
    }
    }