package com.zamazon.controller;

import com.zamazon.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    // ➤ GET /health
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is up and running");
    }

    // ➤ GET /products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "MacBook Pro", 1899.99),
                new Product(2L, "iPhone 14", 999.99),
                new Product(3L, "AirPods Pro", 249.00)
        );
        return ResponseEntity.ok(products);
    }
}
