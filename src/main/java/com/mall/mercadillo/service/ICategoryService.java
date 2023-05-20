package com.mall.mercadillo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mall.mercadillo.model.Category;

public interface ICategoryService {
    public ResponseEntity<List<Category>> search();
    
    public ResponseEntity<List<Category>> save(Category category);

    
}