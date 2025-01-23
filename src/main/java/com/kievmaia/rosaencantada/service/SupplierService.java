package com.kievmaia.rosaencantada.service;

import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.db.repository.ISupplierRepository;
import com.kievmaia.rosaencantada.mapper.SupplierMapper;
import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SupplierService {

    private final ISupplierRepository repository;
    private final SupplierMapper mapper;

    public SupplierResponseDTO save(SupplierRequestDTO requestDTO) {
        var entity = mapper.toEntity.apply(requestDTO);
        var supplier = repository.save(entity);
        return mapper.toResponseDto.apply(supplier);
    }
}
