package com.javaprojects.ecommerce.service;

import com.javaprojects.ecommerce.model.Category;
import com.javaprojects.ecommerce.model.Product;
import com.javaprojects.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public List<Product> findByNameAndCategory(String name, Category category){
        List<Product> byName = findByName(name);
        List<Product> byCategory = findByCategory(category);
        List<Product> result = new ArrayList<>();
        for (Product product : byName) {
            if(byCategory.contains(product)){
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Transactional
    public String addProduct(Product product) {
        productRepository.save(product);
        return "Product added successfully";
    }

    @Transactional
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
        return "Product deleted successfully";
    }
}
