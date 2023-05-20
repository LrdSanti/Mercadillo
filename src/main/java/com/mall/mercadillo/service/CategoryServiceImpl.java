package com.mall.mercadillo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.mercadillo.model.Category;
import com.mall.mercadillo.repository.ICategoryRepository;



@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    List<Category> categories = new ArrayList<>();

    public CategoryServiceImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Category>> search() {
        try {
            categories = (List<Category>) categoryRepository.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(categories, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Category>> save(Category category) {
        try {
            Category categorySaved = categoryRepository.save(category);
            categories.add(categorySaved);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(categories, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }  
}
