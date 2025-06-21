package com.zamazon.controller;

import com.zamazon.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is up and running");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "MacBook Pro", 1899.99),
                new Product(2L, "iPhone 14", 999.99),
                new Product(3L, "AirPods Pro", 249.00),
                new Product(4L, "Samsung Galaxy", 799.99),
                new Product(5L, "Google Pixel", 699.99)
        );
        return ResponseEntity.ok(products);
    }

   @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
    List<Product> products = Arrays.asList(
            new Product(1L, "MacBook Pro", 1899.99),
            new Product(2L, "iPhone 14", 999.99),
            new Product(3L, "AirPods Pro", 249.00),
            new Product(4L, "Samsung Galaxy", 799.99),
            new Product(5L, "Google Pixel", 699.99)
    );

    // Filter by keyword (case-insensitive)
    List<Product> filtered = products.stream()
            .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase()))
            .toList();

    return ResponseEntity.ok(filtered);
}
}
