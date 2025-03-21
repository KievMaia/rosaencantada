package com.kievmaia.rosaencantada.service;

import com.kievmaia.rosaencantada.db.entity.Category;
import com.kievmaia.rosaencantada.db.repository.ICategoryRepository;
import com.kievmaia.rosaencantada.handler.exception.EntityNotFoundException;
import com.kievmaia.rosaencantada.mapper.category.CategoryMapper;
import com.kievmaia.rosaencantada.rest.dto.PagedResponse;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CategoryService {

    private final ICategoryRepository repository;
    private final CategoryMapper mapper;

    @Transactional
    public CategorySummaryDTO save(CategoryRequestDTO requestDTO) {
        var entity = mapper.requestDTOToEntity(requestDTO);
        var category = repository.save(entity);
        return mapper.entityToSummaryDTO(category);
    }

    public PagedResponse<CategoryResponseDTO> getAllCategories(Pageable pageable) {
        var page = repository.findAll(pageable).map(mapper::entityToResponseDTO);
        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

    public CategoryResponseDTO getCategory(Long categoryId) {
        var category = findByIdOrElseThrow(categoryId);
        return mapper.entityToResponseDTO(category);
    }

    public CategoryResponseDTO getCategoryByName(String categoryName) {
        var category = this.findByNameOrElseThrow(categoryName);
        return mapper.entityToResponseDTO(category);
    }

    @Transactional
    public CategorySummaryDTO updateCategory(Long id, CategoryRequestDTO dto) {
        var category = findByIdOrElseThrow(id);
        var entityToUpdate = mapper.toUpdatedDTO(category, dto);
        var supplierUpdated = repository.save(entityToUpdate);
        return mapper.entityToSummaryDTO(supplierUpdated);
    }

    public void deleteCategory(Long id) {
        this.getCategory(id);
        repository.deleteById(id);
    }

    private Category findByIdOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category %s not found", id));
    }

    public Category findByNameOrElseThrow(String categoryName) {
        return repository.findByNameIgnoreCase(categoryName).orElseThrow(
                () -> new EntityNotFoundException("Category %s not found", categoryName));
    }
}
