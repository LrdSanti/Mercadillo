package com.mall.mercadillo.repository;

import org.springframework.data.repository.CrudRepository;

import com.mall.mercadillo.model.Category;

public interface ICategoryRepository extends CrudRepository<Category,Long>{

    // Category save(boolean add);
    
}
