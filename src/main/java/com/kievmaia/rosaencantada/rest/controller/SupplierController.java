package com.kievmaia.rosaencantada.rest.controller;

import com.kievmaia.rosaencantada.rest.dto.PagedResponse;
import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierSummaryDTO;
import com.kievmaia.rosaencantada.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppliers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SupplierController {

    private final SupplierService service;

    @PostMapping
    public ResponseEntity<SupplierSummaryDTO> createSupplier(@Valid @RequestBody SupplierRequestDTO requestDTO) {
        return ResponseEntity.ok(service.save(requestDTO));
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierResponseDTO> getSupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(service.getSupplier(supplierId));
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResponse<SupplierResponseDTO>> getSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ) {
        var pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(service.getAllSuppliers(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierSummaryDTO> updateSupplier(@PathVariable Long id,
                                                             @RequestBody SupplierRequestDTO dto) {
        var updatedSupplier = service.updateSupplier(id, dto);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        service.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}