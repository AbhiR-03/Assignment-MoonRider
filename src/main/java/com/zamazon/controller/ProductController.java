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
public ResponseEntity<?> advancedSearch(
        @RequestParam(required = false) String query,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice
) {
    // Validate input
    if (query == null || query.trim().isEmpty()) {
        return ResponseEntity.badRequest().body("Query parameter 'query' is required and cannot be blank.");
    }

    List<Product> products = Arrays.asList(
            new Product(1L, "MacBook Pro", 1899.99),
            new Product(2L, "iPhone 14", 999.99),
            new Product(3L, "AirPods Pro", 249.00),
            new Product(4L, "Samsung Galaxy", 799.99),
            new Product(5L, "Pixel 7", 599.00)
    );

    List<Product> filtered = products.stream()
            .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase()))
            .filter(p -> minPrice == null || p.getPrice() >= minPrice)
            .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
            .toList();

    if (filtered.isEmpty()) {
        return ResponseEntity.status(404).body("No products found matching your criteria.");
    }

    return ResponseEntity.ok(filtered);
}

}
