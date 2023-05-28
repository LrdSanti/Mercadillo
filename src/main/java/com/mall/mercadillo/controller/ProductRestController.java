package com.mall.mercadillo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mall.mercadillo.model.Product;
import com.mall.mercadillo.service.IProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    
    @Autowired
    public IProductService productService;

    @GetMapping("products")
    public ResponseEntity<List<Product>> searchProducts() {
        return productService.searchProduct();
    }

    @PostMapping("products")
    public ResponseEntity<List<Product>> saveProducts(@RequestParam("picture") MultipartFile picture,
            @RequestParam("name") String name, @RequestParam("price") double price,
            @RequestParam("quantity") int quantity, @RequestParam("categoryId") Long categoryId,
            @RequestParam("makerId") Long makerId) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        
        return productService.save(product, categoryId, makerId);


    }
}
