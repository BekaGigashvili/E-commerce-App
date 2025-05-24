package com.javaprojects.ecommerce.controller;

import com.javaprojects.ecommerce.model.Category;
import com.javaprojects.ecommerce.model.Product;
import com.javaprojects.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public List<Product> findByCategoryOrName(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) Category category){
        if(name != null && category != null){
            return productService.findByNameAndCategory(name, category);
        }else if(name == null && category != null){
            return productService.findByCategory(category);
        }else if(name != null){
            return productService.findByName(name);
        }
        return productService.findAll();
    }
}
