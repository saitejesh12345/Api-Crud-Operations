package com.example.crud_operations.service;


import com.example.crud_operations.entity.Product;
//import com.example.crud_operations.exception.ItemNotFoundException;
import com.example.crud_operations.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


//@ExtendWith(MockitoExtension.class). The @ExtendWith annotation is used to load a
// JUnit 5 extension. JUnit defines an extension API, which allows a third-party
// vendor like Mockito to hook into the lifecycle of running test classes and add additional functionality.
// The MockitoExtension looks at the test class, finds member variables annotated with the @Mock annotation,
// and creates a mock implementation of those variables. It then finds member variables annotated with the
// @InjectMocks annotation and attempts to inject its mocks into those classes, using either construction
// injection or setter injection.


@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

//    MockitoExtension finds the @Mock annotation on the Repository member variable,
//    so it creates a mock implementation of it and assigns it to the repository variable.
//    When it sees the @InjectMocks annotation on the Service member variable, it creates an
//    instance of the Service class, passing the mock Repository to its constructor.
//    This allows us to control the behavior of the mock Repository class using Mockito's APIs.

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;


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

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveProduct method")
    @Test
    public void saveProductTest(){
        Product product = new Product(1,"Redlabel",2,100);
        when(repository.save(product)).thenReturn(product);
        assertEquals(product,service.saveProduct(product));
    }

    @DisplayName("JUnit test for saveProduct method")
    @Test
    public void saveProduct() {
//        // given - precondition or setup
//
      given(repository.save(product)).willReturn(product);
//
//        // when -  action or the behaviour that we are going test
      Product saveProduct = service.saveProduct(product);
//
//        // then - verify the output
      assertThat(saveProduct).isNotNull();
     //   assertNotNull(saveProduct);
//assertEquals(excepted 2,actual(1+1));
//
//
    }
    @DisplayName("JUnit test for saveProduct method")
    @Test
    public void saveTheProduct()
    {

        when(this.repository.save(product)).thenReturn(product);
        //	EmployeeService eService= new EmployeeServiceImpl();
        Product obj= service.saveProduct(product);
        assertThat(obj).isNotNull();
        //assertNotNull(emp);
        // assertEquals("Puneet",emp.getName());
    }

    // JUnit test for saveAll method
    @DisplayName("JUnit test for saveAll method")
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
        repository.saveAll(products);
        List<Product> studentList = repository.findAll();
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
    }


    @DisplayName("JUnit test for getProductTest method")
    @Test
    public void givenProductObject_whenSaveProduct_thenReturnProductObject(){
        // given - precondition or setup
//        given(repository.findByName(product.getName()))
//                .willReturn(Null);
        given(repository.save(product)).willReturn(product);

        // when -  action or the behaviour that we are going test
        Product saveProduct = service.saveProduct(product);

        // then - verify the output
        assertThat(saveProduct).isNotNull();
        // assertNotNull(savedEmployee);
        //assertEquals(excepted 2,actual(1+1));
    }

    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenProductList_whenGetAllProducts_thenReturnProductList(){
        // given - precondition or setup

        Product product1 = Product.builder()
                .id(2)
                .name("Green grams")
                .quantity(4)
                .price(350)
                .build();


        given(repository.findAll()).willReturn(Arrays.asList(product,product1));

        // when -  action or the behaviour that we are going test
        List<Product> ProductList = service.getProducts();

        // then - verify the output
        assertThat(ProductList).isNotNull();
        assertThat(ProductList.size()).isEqualTo(2);



    }
    @DisplayName("JUnit test for getAllEmployees method for negative scenario")
    @Test
    public void givenProductList_whenGetAllProducts_thenReturnEmptyProductList() {
        // given - precondition or setup

        Product product1 = Product.builder()
                .id(2)
                .name("Green grams")
                .quantity(4)
                .price(350)
                .build();


        given(repository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Product> ProductList = service.getProducts();

        // then - verify the output
        // then - verify the output
        assertThat(ProductList).isEmpty();
        assertThat(ProductList.size()).isEqualTo(0);

    }

    @DisplayName("JUnit test for getProductById method")
    @Test
    public void givenProductId_whenGetProductById_thenReturnProductObject(){
        // given
        given(repository.findById(1)).willReturn(Optional.of(product));

        // when
         Product savedproduct =  service.getSingleProduct(product.getId());

        // then
        assertThat(savedproduct).isNotNull();

    }
    //JUnit test for deleteProduct method
    @DisplayName("JUnit test for deleteProduct method")
    @Test
    public void deleteById()
    {
        int productId = 1;
        willDoNothing().given(repository).deleteById( productId );
        service.deleteProduct(productId);
        verify(repository, times(1)).deleteById(productId );
    }
    // JUnit test for deleteProduct method
//    @DisplayName("JUnit test for deleteProduct method")
//    @Test
//    public void givenProductId_whenDeleteProduct_thenNothing(){
////        product = Product.builder()
////                .id(1)
////                .name("Black grams")
////                .quantity(4)
////                .price(350)
////                .build();
//        when(repository.findByName("Black grams")).thenReturn(product);
//        Product obj = service.deleteProduct(product);
//        assertThat(obj).isNotNull();
//         repository.delete(product);
//     //assertThat(obj).isNull();
//    }
//
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
        // repository.save(product1);

        // when -  action or the behaviour that we are going test
       // Product savedEmployee =  repository.findById(product1.getId()).get();
       // Product savedEmployee =  service.updateProduct(product1);
        given(repository.save(product1)).willReturn(product1);
        product1.setName("white-powder");
        product1.setPrice(400);
        Product updatedEmployee =  service.updateProduct(product1);

        // then - verify the output
        assertThat(updatedEmployee.getName()).isEqualTo("white-powder");
        assertThat(updatedEmployee.getPrice()).isEqualTo(400);
    }

}