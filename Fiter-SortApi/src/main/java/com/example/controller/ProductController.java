package com.example.controller;

import com.example.exception.NoProductsFound;
import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
//    @GetMapping("/all")
//    public ResponseEntity<List<Product>> allHandle(){
//        List<Product> products = productService.findAllProduct();
//        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
//    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam Optional<String> category,
            @RequestParam Optional<Double> minPrice,
            @RequestParam Optional<Double> maxPrice,
            @RequestParam Optional<Boolean> inStock,
            @RequestParam Optional<String> sortField,
            @RequestParam Optional<String> sortOrder) throws NoProductsFound {

        List<Product> filterProducts = productService.getFilteredAndSortedProducts(category,minPrice,maxPrice,inStock,sortField,sortOrder);

        return new ResponseEntity<>(filterProducts,HttpStatus.OK);

    }

}
