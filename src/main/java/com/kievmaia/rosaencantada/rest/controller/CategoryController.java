package com.kievmaia.rosaencantada.rest.controller;

import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierSummaryDTO;
import com.kievmaia.rosaencantada.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategorySummaryDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(service.save(requestDTO));
    }

//    @GetMapping("/{supplierId}")
//    public ResponseEntity<SupplierResponseDTO> getSupplier(@PathVariable Long supplierId) {
//        return ResponseEntity.ok(service.getSupplier(supplierId));
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<PagedResponse<SupplierResponseDTO>> getSuppliers(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "name") String sortBy
//    ) {
//        var pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        return ResponseEntity.ok(service.getAllSuppliers(pageable));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<SupplierSummaryDTO> updateSupplier(@PathVariable Long id,
//                                                             @RequestBody SupplierRequestDTO dto) {
//        var updatedSupplier = service.updateSupplier(id, dto);
//        return ResponseEntity.ok(updatedSupplier);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
//        service.deleteSupplier(id);
//        return ResponseEntity.noContent().build();
//    }
}