package com.example.geh.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.geh.dao.ProductRepository;
import com.example.geh.exception.ResourceNotFoundException;
import com.example.geh.model.Product;

@Service
public class ProductService {

    private static final Logger Logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   
    public Product getProductById(Long id) {
        Logger.info("Fetching the product with id : {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found with this id :" + id));
    }

  
    public Collection<Product> getAllProducts() {
        Logger.info("Fetching All Products");
        return productRepository.findAll();
    }

    
    public Product saveProduct(Product product) {
        Logger.info("Saving product: {}", product.getName());
        return productRepository.save(product);
    }

    
    public Product updateProduct(Long id, Product productDetails) {
        Logger.info("Updating product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found with this id :" + id));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    
    public void deleteProduct(Long id) {
        Logger.info("Deleting product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found with this id :" + id));
        productRepository.delete(product);
    }
}