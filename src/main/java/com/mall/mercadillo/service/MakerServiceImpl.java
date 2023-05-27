package com.mall.mercadillo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        
        try {
            Optional<Maker> maker = makerRepository.findById(id);

            if (maker.isPresent()) {
                
                return new ResponseEntity<>(maker.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Maker>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Maker>> update(Maker maker, Long id) {
        try {
            Optional<Maker> makerSearch = makerRepository.findById(id);

            if (makerSearch.isPresent()) {
                makerSearch.get().setName(maker.getName());
                
                Maker makerToUpdate = makerRepository.save(makerSearch.get());
                makers.add(makerToUpdate);

                return new ResponseEntity<>(makers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Maker>> delete(Long id) {
        
        try {
            makerRepository.deleteById(id);
            return new ResponseEntity<>(makers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
