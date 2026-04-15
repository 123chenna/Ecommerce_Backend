package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    // ✅ Get all products
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // ✅ Get product by ID (IMPORTANT for product.html)
    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // ✅ Add product (optional)
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    // ✅ Delete product (optional)
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
    
}