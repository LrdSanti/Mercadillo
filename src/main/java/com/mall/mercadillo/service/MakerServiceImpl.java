package com.mall.mercadillo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.mercadillo.model.Maker;
import com.mall.mercadillo.repository.IMakerRepository;



@Service
public class MakerServiceImpl implements IMakerService {

    
    private final IMakerRepository makerRepository;

    public MakerServiceImpl(IMakerRepository makerRepository) {
        this.makerRepository = makerRepository;
    }

    
    public List<Maker> makers = new ArrayList<>();

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Maker>> search() {
        
        try {
            makers = (List<Maker>) makerRepository.findAll();
            return new ResponseEntity<List<Maker>>(makers, HttpStatus.OK); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Maker>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Maker>> save(Maker maker) {
        
        try {
            
            Maker makerSaved = makerRepository.save(maker);
            makers.add(makerSaved);

            return new ResponseEntity<>(makers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Maker> searchById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchById'");
    }

    @Override
    public ResponseEntity<List<Maker>> update(Maker maker, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ResponseEntity<List<Maker>> delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
