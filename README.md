# Filter-Sort API with Spring Boot and MongoDB 🌟

## Overview

This Spring Boot application provides a robust API for filtering and sorting products stored in a MongoDB database. The API allows clients to retrieve products based on various criteria such as category, price range, and stock availability, and sort the results by price, rating, or creation date.

### Key Features

- **Flexible Filtering**: Filter products by category, price range, and stock availability.
- **Dynamic Sorting**: Sort products by price, rating, or creation date in ascending or descending order.
- **Exception Handling**: Gracefully handles invalid inputs and scenarios where no products are found.
- **Comprehensive Testing**: Unit tests ensure the reliability and correctness of the filtering and sorting logic.


## Getting Started

### Prerequisites

- **Java 11 or higher**: Ensure you have JDK 11 or a higher version installed.
- **Maven**: Make sure Maven is installed on your system.
- **MongoDB**: You should have a MongoDB instance running. You can use MongoDB Atlas or a local MongoDB instance.

### Running the Application

1. **Clone the Repository**

   ```sh
   git clone <repository-url>
   cd <repository-directory>
2. **Update the application.properties file with your MongoDB URI:**
 `spring.application.name=Filter-SortApi`

`spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.ex9rztl.mongodb.net/yourDatabaseName?retryWrites=true&w=majority&appName=Cluster0`

Replace `<username>`, `<password>`, and yourDatabaseName with your actual MongoDB credentials and database name.

4. **Fetching Products**

 GET "http://localhost:8080/products?category=electronics&minPrice=100&maxPrice=1000&inStock=true&sortField=price&sortOrder=asc"


### Sample Data


![test1](https://github.com/user-attachments/assets/e2e48e80-c0a2-4eb4-9f64-58062a0d6958)


![test2](https://github.com/user-attachments/assets/c7919bc1-f9a4-4293-9eb7-37c30e347f18)
![test3](https://github.com/user-attachments/assets/282e07ae-ca22-457c-8ea7-24e6a5f6d67b)

### **Explanation of Sections:**

1. **Overview**: Provides a brief introduction to the project and its key features.
2. **Project Structure**: Shows the directory structure of the project.
3. **Getting Started**: Lists the prerequisites and steps to run the application, including updating configurations and running the application using Maven.
4. **Using the API**: Provides example commands to add and fetch products using the API.
5. **Sample Data**: Instructions for loading sample data into MongoDB.
6. **Testing**: Instructions for running the unit tests.


This `README.md` file should make your project easy to understand and run for anyone who reads it. Good luck! 🚀