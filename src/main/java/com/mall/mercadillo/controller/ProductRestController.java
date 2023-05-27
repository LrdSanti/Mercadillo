package com.mall.mercadillo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mall.mercadillo.service.IProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    
    @Autowired
    public IProductService productService;
}
