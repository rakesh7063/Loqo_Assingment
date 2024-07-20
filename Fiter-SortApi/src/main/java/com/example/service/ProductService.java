package com.example.service;

import com.example.exception.NoProductsFound;
import com.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
//    public List<Product> findAllProduct();
    public List<Product> getFilteredAndSortedProducts(Optional<String> category, Optional<Double> minPrice, Optional<Double> maxPrice, Optional<Boolean> inStock, Optional<String> sortField, Optional<String> sortOrder) throws NoProductsFound;
}
