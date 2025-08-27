package com.example.geh.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.geh.model.Product;
import com.example.geh.service.ProductService;

@RestController
@RequestMapping
public class ProductController {

    @Autowired 
    public ProductService productService;
    
    @GetMapping("/welcome")
    public String message() {
        return "Product Example! It's working";
    }
    
    //Parameterized Mapping
    @GetMapping("view/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    
    @GetMapping("viewall")
    public Collection<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}