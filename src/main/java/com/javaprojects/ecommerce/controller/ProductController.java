package com.javaprojects.ecommerce.controller;

import com.javaprojects.ecommerce.model.Category;
import com.javaprojects.ecommerce.model.Product;
import com.javaprojects.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @PostMapping("/remove/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
    @GetMapping("/categories")
    public Category[] getCategories(){
        return Category.values();
    }
}
