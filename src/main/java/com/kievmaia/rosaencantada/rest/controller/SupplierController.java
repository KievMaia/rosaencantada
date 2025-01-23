package com.kievmaia.rosaencantada.rest.controller;

import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/suppliers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SupplierController {

    private final SupplierService service;

    @PostMapping
    public ResponseEntity<Void> createSupplier(@RequestParam SupplierRequestDTO requestDTO){
        service.save(requestDTO);
        return ResponseEntity.ok().build();
    }
}
