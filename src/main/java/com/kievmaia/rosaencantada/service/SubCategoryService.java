package com.kievmaia.rosaencantada.service;

import com.kievmaia.rosaencantada.db.repository.ISubCategoryRepository;
import com.kievmaia.rosaencantada.mapper.subcategory.SubCategoryMapper;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import com.kievmaia.rosaencantada.rest.dto.subcategory.SubCategoryRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubCategoryService {

    private final ISubCategoryRepository repository;
    private final SubCategoryMapper mapper;

    private final CategoryService categoryService;

    @Transactional
    public CategorySummaryDTO save(SubCategoryRequestDTO requestDTO) {
        var categoryEntity = categoryService.findByNameOrElseThrow(requestDTO.getCategoryName());
        var subCategoryEntity = mapper.requestDTOToEntity(requestDTO, categoryEntity);
        repository.save(subCategoryEntity);
        return mapper.entityToSummaryDTO(categoryEntity);
    }

//    public PagedResponse<CategoryResponseDTO> getAllCategories(Pageable pageable) {
//        var page = repository.findAll(pageable).map(mapper::entityToResponseDTO);
//        return new PagedResponse<>(
//                page.getContent(),
//                page.getNumber(),
//                page.getSize(),
//                page.getTotalElements(),
//                page.getTotalPages(),
//                page.isLast()
//        );
//    }
//
//    public CategoryResponseDTO getCategory(Long categoryId) {
//        var category = findByIdOrElseThrow(categoryId);
//        return mapper.entityToResponseDTO(category);
//    }
//
//    public CategoryResponseDTO getCategoryByName(String categoryName) {
//        var category = repository.findByNameIgnoreCase(categoryName).orElseThrow(
//                () -> new EntityNotFoundException("Category %s not found", categoryName));
//        return mapper.entityToResponseDTO(category);
//    }
//
//    @Transactional
//    public CategorySummaryDTO updateCategory(Long id, CategoryRequestDTO dto) {
//        var category = findByIdOrElseThrow(id);
//        var entityToUpdate = mapper.toUpdatedDTO(category, dto);
//        var supplierUpdated = repository.save(entityToUpdate);
//        return mapper.entityToSummaryDTO(supplierUpdated);
//    }
//
//    public void deleteCategory(Long id) {
//        this.getCategory(id);
//        repository.deleteById(id);
//    }
//
//    private Category findByIdOrElseThrow(Long id) {
//        return repository.findById(id).orElseThrow(
//                () -> new EntityNotFoundException("Category %s not found", id));
//    }
}
