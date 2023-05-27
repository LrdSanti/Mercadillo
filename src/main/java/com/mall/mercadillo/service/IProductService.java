package com.mall.mercadillo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mall.mercadillo.model.Product;

public interface IProductService {
    public ResponseEntity<List<Product>> searchProduct();
    
    public ResponseEntity<List<Product>> save(Product product, Long categoryId, Long MakerId);


    // Update
    public ResponseEntity<List<Product>> update(Product product, Long categoryId, Long makerId, Long productId);


    // Eliminar   
}
