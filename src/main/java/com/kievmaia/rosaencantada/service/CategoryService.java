package com.kievmaia.rosaencantada.service;

import com.kievmaia.rosaencantada.db.repository.ICategoryRepository;
import com.kievmaia.rosaencantada.mapper.category.CategoryMapper;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

//    public PagedResponse<SupplierResponseDTO> getAllSuppliers(Pageable pageable) {
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
//    public SupplierResponseDTO getSupplier(Long supplierId) {
//        var supplier = repository.findById(supplierId).orElseThrow(
//                () -> new EntityNotFoundException("Supplier not found"));
//        return mapper.entityToResponseDTO(supplier);
//    }
//
//    @Transactional
//    public SupplierSummaryDTO updateSupplier(Long id, CategoryRequestDTO dto) {
//        var supplier = this.getSupplier(id);
//        var dtoSupplierToUpdate = mapper.toUpdatedDTO(supplier, dto);
//        var entityToUpdate = mapper.requestDTOToEntity(dtoSupplierToUpdate);
//        var supplierUpdated = repository.save(entityToUpdate);
//        return mapper.entityToSummaryDTO(supplierUpdated);
//    }
//
//    public void deleteSupplier(Long id) {
//        this.getSupplier(id);
//        repository.deleteById(id);
//    }
}
