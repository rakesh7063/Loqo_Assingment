package com.example.service;

import com.example.exception.NoProductsFound;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    // List of valid fields for sorting to ensure only these fields can be used in sort queries
    private static final List<String> VALID_SORT_FIELDS = List.of("price", "rating", "createdAt");


    @Override
    public List<Product> getFilteredAndSortedProducts(Optional<String> category, Optional<Double> minPrice, Optional<Double> maxPrice, Optional<Boolean> inStock, Optional<String> sortField, Optional<String> sortOrder)throws NoProductsFound {
        Query query = new Query();

        // Filtering
        if (category.isPresent() || minPrice.isPresent() || maxPrice.isPresent() || inStock.isPresent()) {
            Criteria criteria = new Criteria();
        // Apply category filter if present
            category.ifPresent(cat -> criteria.and("category").is(cat));

            // Apply price range filter if present
            if (minPrice.isPresent() || maxPrice.isPresent()) {
                Criteria priceCriteria = new Criteria("price");
                minPrice.ifPresent(priceCriteria::gte);
                maxPrice.ifPresent(priceCriteria::lte);
                criteria.andOperator(priceCriteria);
            }
            // Apply stock availability filter if present
            inStock.ifPresent(stock -> criteria.and("inStock").is(stock));

            query.addCriteria(criteria);
        }

        // Sorting
        if (sortField.isPresent()) {

            Sort.Direction direction = sortOrder.orElse("asc").equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            query.with(Sort.by(direction, sortField.get()));
        } else {
            query.with(Sort.by(Sort.Direction.ASC, "createdAt"));
        }

        List<Product> newFilteredAndSortedProducts = mongoTemplate.find(query, Product.class);
        if (newFilteredAndSortedProducts.isEmpty()) {
            throw new NoProductsFound("No products found");
        }
        return newFilteredAndSortedProducts;
    }
}
