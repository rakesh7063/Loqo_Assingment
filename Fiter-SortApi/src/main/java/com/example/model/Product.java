package com.example.model;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "products")
@ToString
public class Product {
    @Id
    private String id;
    private String name;
    private String category;
    private double price;
    private boolean inStock;
    private double rating;
    private Date createdAt;
}