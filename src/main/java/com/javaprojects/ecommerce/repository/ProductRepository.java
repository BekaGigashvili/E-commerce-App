package com.javaprojects.ecommerce.repository;

import com.javaprojects.ecommerce.model.Category;
import com.javaprojects.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    List<Product> findByName(String name);
}
