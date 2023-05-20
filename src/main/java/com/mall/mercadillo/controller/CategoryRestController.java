package com.mall.mercadillo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mall.mercadillo.model.Category;
import com.mall.mercadillo.service.ICategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("categories")
    public ResponseEntity<List<Category>> searchCategories(){
        return categoryService.search();
    }

    @PostMapping("categories")
    public ResponseEntity<List<Category>> saveCategorie(@RequestBody
    Category category){
        return categoryService.save(category);
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<Category> searchCategoryById(@PathVariable Long id){
        return categoryService.searchById(id);
}

@PutMapping("categories/{id}")
public ResponseEntity<List<Category>> updateCategoryB(@RequestBody Category category, @PathVariable Long id){
    return categoryService.update(category, id);
}
    
}
