package com.mall.mercadillo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mall.mercadillo.model.Product;

public interface IProductService {
    public ResponseEntity<List<Product>> searchProduct();
    
}
