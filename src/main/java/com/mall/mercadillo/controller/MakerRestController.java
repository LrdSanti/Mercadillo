package com.mall.mercadillo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mall.mercadillo.model.Maker;
import com.mall.mercadillo.service.IMakerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class MakerRestController {
    
    @Autowired
    private IMakerService makerService;

    @GetMapping("makers")
    public ResponseEntity<List<Maker>> searchMakers() {
        return makerService.search();
    }

    @PostMapping("makers")
    public ResponseEntity<List<Maker>> saveMaker(@RequestBody Maker maker) {
        return makerService.save(maker);
    }

    @GetMapping("makers/{id}")
    public ResponseEntity<Maker> searchMakerById(@PathVariable Long id) {
        return makerService.searchById(id);
    }

    @PutMapping("makers/{id}")
    public ResponseEntity<List<Maker>> updateMaker(@RequestBody Maker maker, @PathVariable Long id) {
        return makerService.update(maker, id);
    }

    @DeleteMapping("makers/{id}")
    public ResponseEntity<List<Maker>> deleteMaker(@PathVariable Long id) {
        return makerService.delete(id);
    }
}
