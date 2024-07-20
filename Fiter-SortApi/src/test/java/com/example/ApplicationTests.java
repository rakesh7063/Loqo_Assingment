package com.example;

import com.example.exception.NoProductsFound;
import com.example.model.Product;
import com.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ApplicationTests {
	@Autowired
	private ProductService productService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@BeforeEach
	public void setUp() {
		// Clear the products collection before each test
		mongoTemplate.dropCollection(Product.class);

		// Add some test data
		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setCategory("electronics");
		product1.setPrice(150.0);
		product1.setInStock(true);
		product1.setRating(4.0);
		product1.setCreatedAt(new Date());
		mongoTemplate.save(product1);

		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setCategory("electronics");
		product2.setPrice(200.0);
		product2.setInStock(false);
		product2.setRating(3.5);
		product2.setCreatedAt(new Date());
		mongoTemplate.save(product2);
	}

	@Test
	public void testGetFilteredAndSortedProducts() throws NoProductsFound {
		List<Product> products = productService.getFilteredAndSortedProducts(
				Optional.of("electronics"),
				Optional.of(100.0),
				Optional.of(200.0),
				Optional.of(true),
				Optional.of("price"),
				Optional.of("asc")
		);

		assertThat(products).hasSize(1);
		assertThat(products.get(0).getName()).isEqualTo("Product 1");
	}

	@Test
	public void testNoProductsFoundException() {
		// Attempt to fetch products with filters that will result in no matches
		NoProductsFound exception = assertThrows(NoProductsFound.class, () -> {
			productService.getFilteredAndSortedProducts(
					Optional.of("non-existent-category"),
					Optional.empty(),
					Optional.empty(),
					Optional.empty(),
					Optional.of("price"),
					Optional.of("asc")
			);
		});

		assertThat(exception.getMessage()).isEqualTo("No products found");
	}
}
