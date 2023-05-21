package com.mall.mercadillo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.mercadillo.model.Product;
import com.mall.mercadillo.repository.IProductoRepository;
import com.mall.mercadillo.utili.Util;

@Service
public class ProductoServiceImpl implements IProductService {
    private final IProductoRepository productoRepository;

    private final List<Product> products = new ArrayList<>();

    public ProductoServiceImpl(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
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

}
