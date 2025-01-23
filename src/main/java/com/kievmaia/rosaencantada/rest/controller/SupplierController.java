package com.kievmaia.rosaencantada.rest.controller;

import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/suppliers")
public class SupplierController {

    @PostMapping
    public ResponseEntity<Void> createSupplier(@RequestParam SupplierRequestDTO requestDTO){
        // TODO: Implement the logic to create a new supplier
        return ResponseEntity.ok().build();
    }
}
