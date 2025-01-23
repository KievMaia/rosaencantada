package com.kievmaia.rosaencantada.mapper;

import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierResponseDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SupplierMapper {
    public final Function<SupplierRequestDTO, Supplier> toEntity = dto -> Supplier.builder()
            .setName(dto.getName())
            .setCnpj(dto.getCnpj())
            .setPhone(dto.getPhone())
            .setEmail(dto.getEmail())
            .setAddress(dto.getAddress())
            .build();

    public final Function<Supplier, SupplierRequestDTO> toDto = entity -> SupplierRequestDTO.builder()
            .setName(entity.getName())
            .setCnpj(entity.getCnpj())
            .setPhone(entity.getPhone())
            .setEmail(entity.getEmail())
            .setAddress(entity.getAddress())
            .build();

    public final Function<Supplier, SupplierResponseDTO> toResponseDto = entity -> SupplierResponseDTO.builder()
            .setId(entity.getId())
            .setName(entity.getName())
            .build();
}
