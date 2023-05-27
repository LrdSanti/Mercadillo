package com.mall.mercadillo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mall.mercadillo.model.Maker;

public interface IMakerService {

    public ResponseEntity<List<Maker>> search();
    public ResponseEntity<List<Maker>> save(Maker maker);
    public ResponseEntity<Maker> searchById(Long id);
    public ResponseEntity<List<Maker>> update(Maker maker, Long id);
    public ResponseEntity<List<Maker>> delete(Long id);
    
}
