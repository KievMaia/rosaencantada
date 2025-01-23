package com.kievmaia.rosaencantada.rest.controller;

import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierResponseDTO;
import com.kievmaia.rosaencantada.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppliers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SupplierController {

    private final SupplierService service;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody SupplierRequestDTO requestDTO){
        return ResponseEntity.ok(service.save(requestDTO));
    }
}
