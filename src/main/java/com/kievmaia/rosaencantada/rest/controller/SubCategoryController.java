package com.kievmaia.rosaencantada.rest.controller;

import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import com.kievmaia.rosaencantada.rest.dto.subcategory.SubCategoryRequestDTO;
import com.kievmaia.rosaencantada.service.SubCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/subcategories")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubCategoryController {

    private final SubCategoryService service;

    @PostMapping
    public ResponseEntity<CategorySummaryDTO> createSubCategory(@Valid @RequestBody SubCategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(service.save(requestDTO));
    }

//    @GetMapping("/{categoryId}")
//    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable Long categoryId) {
//        return ResponseEntity.ok(service.getCategory(categoryId));
//    }
//
//    @GetMapping("/byname/{categoryName}")
//    public ResponseEntity<CategoryResponseDTO> getCategoryByName(@PathVariable String categoryName) {
//        return ResponseEntity.ok(service.getCategoryByName(categoryName));
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<PagedResponse<CategoryResponseDTO>> getCategories(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "name") String sortBy
//    ) {
//        var pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        return ResponseEntity.ok(service.getAllCategories(pageable));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CategorySummaryDTO> updateCategory(@PathVariable Long id,
//                                                             @RequestBody CategoryRequestDTO dto) {
//        var updatedCategory = service.updateCategory(id, dto);
//        return ResponseEntity.ok(updatedCategory);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
//        service.deleteCategory(id);
//        return ResponseEntity.noContent().build();
//    }
}