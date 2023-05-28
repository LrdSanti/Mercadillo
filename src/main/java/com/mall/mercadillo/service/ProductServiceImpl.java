package com.mall.mercadillo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.mercadillo.model.Category;
import com.mall.mercadillo.model.Maker;
import com.mall.mercadillo.model.Product;
import com.mall.mercadillo.repository.ICategoryRepository;
import com.mall.mercadillo.repository.IMakerRepository;
import com.mall.mercadillo.repository.IProductRepository;
import com.mall.mercadillo.utili.Util;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productoRepository;

    private ICategoryRepository categoryRepository;
    private IMakerRepository makerRepository;

    private final List<Product> products = new ArrayList<>();

    public ProductServiceImpl(IProductRepository productoRepository, ICategoryRepository categoryRepository,
            IMakerRepository makerRepository) {
        this.productoRepository = productoRepository;
        this.categoryRepository = categoryRepository;
        this.makerRepository = makerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> searchProduct() {
        List<Product> listAux;
        try {
            listAux = (List<Product>) productoRepository.findAll();
            if (listAux.size() > 0) {
                listAux.forEach(p -> {
                    byte[] imageDescompressed = Util.decompressZLib(p.getPicture());
                    p.setPicture(imageDescompressed);
                    products.add(p);
                });
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Product>> save(Product product, Long categoryId, Long makerId) {
        
        try {

            Optional<Category> category = categoryRepository.findById(categoryId);
            Optional<Maker> maker = makerRepository.findById(makerId);

            if (category.isPresent() && maker.isPresent()) {

                product.setCategory(category.get());
                product.setMaker(maker.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Product productSaved = productoRepository.save(product);
            if (productSaved != null) {
                products.add(productSaved);
            } else {
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<List<Product>> update(Product product, Long categoryId, Long makerId, Long productId) {
        try {
            Optional<Category> category = categoryRepository.findById(categoryId);
            Optional<Maker> maker = makerRepository.findById(categoryId);
            if (category.isPresent() && maker.isPresent()) {
                product.setCategory(category.get());
                product.setMaker(maker.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Optional<Product> productSearch = productoRepository.findById(productId);
            if (productSearch.isPresent()) {
                productSearch.get().setName(product.getName());
                productSearch.get().setPrice(product.getPrice());
                productSearch.get().setPicture(product.getPicture());
                productSearch.get().setCategory(product.getCategory());
                productSearch.get().setMaker(product.getMaker());

                Product productSaved = productoRepository.save(productSearch.get());
                if (productSaved != null) {
                    products.add(productSaved);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
