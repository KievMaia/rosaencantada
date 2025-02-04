package com.kievmaia.rosaencantada.service;

import com.kievmaia.rosaencantada.db.repository.ISupplierRepository;
import com.kievmaia.rosaencantada.handler.exception.EntityNotFoundException;
import com.kievmaia.rosaencantada.mapper.SupplierMapper;
import com.kievmaia.rosaencantada.rest.dto.PagedResponse;
import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierSummaryDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SupplierService {

    private final ISupplierRepository repository;
    private final SupplierMapper mapper;

    @Transactional
    public SupplierSummaryDTO save(SupplierRequestDTO requestDTO) {
        var entity = mapper.requestDTOToEntity(requestDTO);
        var supplier = repository.save(entity);
        return mapper.entityToSummaryDTO(supplier);
    }

    public PagedResponse<SupplierResponseDTO> getAllSuppliers(Pageable pageable) {
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

    public SupplierResponseDTO getSupplier(Long supplierId) {
        var supplier = repository.findById(supplierId).orElseThrow(
                () -> new EntityNotFoundException("Supplier not found"));
        return mapper.entityToResponseDTO(supplier);
    }

    @Transactional
    public SupplierSummaryDTO updateSupplier(Long id, SupplierRequestDTO dto) {
        var supplier = this.getSupplier(id);
        var dtoSupplierToUpdate = mapper.toUpdatedDTO(supplier, dto);
        var entityToUpdate = mapper.requestDTOToEntity(dtoSupplierToUpdate);
        var supplierUpdated = repository.save(entityToUpdate);
        return mapper.entityToSummaryDTO(supplierUpdated);
    }

    public void deleteSupplier(Long id) {
        this.getSupplier(id);
        repository.deleteById(id);
    }
}
