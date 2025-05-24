package com.javaprojects.ecommerce.config;

import com.javaprojects.ecommerce.model.Category;
import com.javaprojects.ecommerce.model.Product;
import com.javaprojects.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

//    @Bean
//    public CommandLineRunner loadData(ProductRepository productRepository) {
//        return args -> {
//            productRepository.save(new Product(null, "Smartphone", "Latest Android smartphone", Category.ELECTRONICS, 699.99));
//            productRepository.save(new Product(null, "Laptop", "15-inch laptop with SSD", Category.ELECTRONICS, 999.00));
//            productRepository.save(new Product(null, "T-Shirt", "Cotton round neck t-shirt", Category.CLOTHING, 19.99));
//            productRepository.save(new Product(null, "Jeans", "Slim fit jeans", Category.CLOTHING, 49.99));
//            productRepository.save(new Product(null, "Running Shoes", "Comfortable running shoes", Category.FOOTWEAR, 79.99));
//            productRepository.save(new Product(null, "Sneakers", "Stylish sneakers", Category.FOOTWEAR, 89.50));
//            productRepository.save(new Product(null, "Gold Necklace", "22K gold necklace", Category.JEWELRY, 1299.99));
//            productRepository.save(new Product(null, "Diamond Ring", "1 carat diamond ring", Category.JEWELRY, 1999.99));
//            productRepository.save(new Product(null, "Sofa", "3-seater fabric sofa", Category.FURNITURE, 549.99));
//            productRepository.save(new Product(null, "Dining Table", "Wooden dining table set", Category.FURNITURE, 799.00));
//            productRepository.save(new Product(null, "Lipstick", "Matte finish lipstick", Category.BEAUTY, 12.99));
//            productRepository.save(new Product(null, "Foundation", "Liquid foundation", Category.BEAUTY, 17.50));
//            productRepository.save(new Product(null, "Football", "Standard size football", Category.SPORTS, 24.99));
//            productRepository.save(new Product(null, "Yoga Mat", "Non-slip yoga mat", Category.SPORTS, 29.99));
//            productRepository.save(new Product(null, "Harry Potter", "Harry Potter and the Philosopher's Stone", Category.BOOKS, 14.99));
//            productRepository.save(new Product(null, "Atomic Habits", "Self-improvement book", Category.BOOKS, 21.99));
//            productRepository.save(new Product(null, "Lego Set", "Space-themed lego set", Category.TOYS, 39.99));
//            productRepository.save(new Product(null, "Puzzle", "1000-piece jigsaw puzzle", Category.TOYS, 14.95));
//            productRepository.save(new Product(null, "Organic Honey", "Raw organic honey", Category.FOOD, 8.99));
//            productRepository.save(new Product(null, "Pasta", "Italian wheat pasta", Category.FOOD, 3.49));
//            productRepository.save(new Product(null, "Vitamin C", "Immunity booster supplement", Category.HEALTH, 11.99));
//            productRepository.save(new Product(null, "Protein Powder", "Whey protein for fitness", Category.HEALTH, 34.99));
//            productRepository.save(new Product(null, "Table Lamp", "LED study lamp", Category.HOME, 25.00));
//            productRepository.save(new Product(null, "Curtains", "Decorative window curtains", Category.HOME, 45.99));
//        };
//    }
}
