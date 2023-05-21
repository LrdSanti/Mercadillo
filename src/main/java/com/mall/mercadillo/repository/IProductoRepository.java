package com.mall.mercadillo.repository;

import org.springframework.data.repository.CrudRepository;

import com.mall.mercadillo.model.Product;

public interface IProductoRepository extends CrudRepository<Product, Long> {

}
