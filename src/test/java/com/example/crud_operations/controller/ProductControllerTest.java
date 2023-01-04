package com.example.crud_operations.controller;


import com.example.crud_operations.entity.Product;
import com.example.crud_operations.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest //this annotation will load all the component that is required to test the employee controller
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc; //So we're going to use MockMvc to call REST API.

    @MockBean
    private ProductService productService;
   // private WebApplicationContext webApplicationContext;
//it will tell spring that create this mock object, that is productService and
    //register in an application context so that this mock object can be available to
    //the product controller
    //Main difference between @MockBean and @Mock annotation is that @MockBean creates
    //mock and injects it into Application Context, while @Mock annotation only creates it,
    //if you want to inject it, you can do it manually or with @InjectMocks annotation,
    //however injection is being done to the class not whole Application Context.


    @Autowired
    private ObjectMapper objectMapper; //Object Mapper Jackson class to serialize and decentralize java objects.

private Product product;
    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .id(1)
                .name("Black grams")
                .quantity(4)
                .price(350)
                .build();
//
//   
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    // JUnit test to save the product  REST API
    @Test
    public void add_product_and_save_it() throws Exception{
        Product product2 = new  Product();
        product2.setId(2);
        product2.setName("Red grams");
        product2.setQuantity(5);
        product2.setPrice(900);

        String inputInJson = this.mapToJson(product2);
        String URI= "/api/crud/addProduct";
        when(productService.saveProduct(any(Product.class))).thenReturn(product2);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
                .content(inputInJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }
    // JUnit test to save the list of products  REST API

    @Test
    public void add_list_of_products_and_SaveAll() throws Exception {
        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(Product.builder()
                .id(3)
                .name("sugar grains")
                .quantity(4)
                .price(450)
                .build());
        listOfProducts.add(Product.builder()
                .id(4)
                .name("Black Rice")
                .quantity(4)
                .price(750)
                .build());


        String inputInJson = this.mapToJson(listOfProducts);
        String URI = "/api/crud/addProducts";
        when(productService.saveProducts(any())).thenReturn(listOfProducts);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
                .content(inputInJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

//        given(productService.getProducts()).willReturn(listOfProducts);
//        ResultActions response =mockMvc.perform(post("/api/crud/addProducts"));
//        response.andExpect(status().isOk()) .andDo(print()).andExpect(jsonPath("$.size()", is(listOfProducts.size())));

        //given(productService.getProducts()).willReturn(listOfProducts);
        //So this is called basically the stubbing method call(mocking)
        //So we are basically stubbing this method call that is getProducts()
        //we are basically providing a response for this method call here.
        //So whenever we will make an actual execution, then that time this is going to return a list of products.

        // when -  action or the behaviour that we are going test
       // ResultActions response =mockMvc.perform(post("/api/crud/addProducts"));
        //OK, now what we have done, we have made a get employee REST API call, and we have stored the response
        //So basically, this rest API returns, the array of JSON.


        // then - verify the output
        // Well, what we are going to verify, we are going to verify their status code in the response of the
        // rest API, as well as the record in the response of the rest.
//        response.andExpect(status().isOk()) //We are going to validate isOK() because this REST API returns ok status code, so we call isOK()
//               .andDo(print()) // we're going to call andDo() method, and we will just print the response of the rest api.
//               .andExpect(jsonPath("$.size()", is(listOfProducts.size())));
        //REST API return array of json object And we are going to basically check the size of the JSON array.



        // JUnit test for Get All employees REST API
    @Test
public void getAllProducts() throws Exception {
        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(product.builder()
                .id(3)
                .name("sugar grains")
                .quantity(4)
                .price(450)
                .build());
        listOfProducts.add(Product.builder()
                .id(4)
                .name("Black Rice")
                .quantity(4)
                .price(750)
                .build());

                       given(productService.getProducts()).willReturn(listOfProducts);
                ResultActions response = mockMvc.perform(get("/api/crud/products"));
                response.andExpect(status().isOk()) .
                andDo(print()).andExpect(jsonPath("$.size()", is(listOfProducts.size())));
    }
//
//        String inputInJson = this.mapToJson(listOfProducts);
//        String URI = "/api/crud/products";
//        when(productService.getProducts()).thenReturn(listOfProducts);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)
//                .content(inputInJson).contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//       String outputInJson = response.getContentAsString();
//      assertThat(outputInJson).isEqualTo(inputInJson);
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//}




    // JUnit test for Get  Product By id REST API
@Test
public Object getProductByIDTest() throws Exception {
   Product product3 = Product.builder()
            .id(4)
            .name("orange grams")
            .quantity(4)
            .price(350)
            .build();
    given(productService.getSingleProduct(product3.getId())).willReturn(product3);
    ResultActions response = mockMvc.perform(get("/product/{id}",product3.getId()));
    response.andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.Name", is(product3.getName())))
            .andExpect(jsonPath("$.quantity", is(product3.getQuantity())))
            .andExpect(jsonPath("$.price", is(product3.getPrice())));
    return null;
}
//delete product
//    @Test
//    public void deleteProductByID() throws Exception
//    {
//        int productId =2;
//        willDoNothing().given(productService).deleteProduct(productId);
//        ResultActions response = mockMvc.perform(delete("/delete/{id}"));
//        response.andExpect(status().isOk())//checked the status
//            .andDo(print());
//    }




        private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }



}
