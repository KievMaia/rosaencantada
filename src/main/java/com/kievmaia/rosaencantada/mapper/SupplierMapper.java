package com.kievmaia.rosaencantada.mapper;

import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierSummaryDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SupplierMapper {
    public Function<SupplierRequestDTO, Supplier> requestDTOToEntity = dto -> Supplier.builder()
            .setId(dto.getId())
            .setName(dto.getName())
            .setCnpj(dto.getCnpj())
            .setPhone(dto.getPhone())
            .setEmail(dto.getEmail())
            .setAddress(dto.getAddress())
            .build();

    public Function<Supplier, SupplierResponseDTO> entityToResponseDTO = entity -> SupplierResponseDTO.builder()
            .setId(entity.getId())
            .setName(entity.getName())
            .setCnpj(entity.getCnpj())
            .setPhone(entity.getPhone())
            .setEmail(entity.getEmail())
            .setAddress(entity.getAddress())
            .setCreatedDate(entity.getCreatedDate())
            .setUpdatedDate(entity.getUpdatedDate())
            .build();

    public Function<Supplier, SupplierSummaryDTO> entityToSummaryDTO = entity -> SupplierSummaryDTO.builder()
            .setId(entity.getId())
            .setName(entity.getName())
            .build();

    public Function<SupplierResponseDTO, SupplierRequestDTO> toUpdatedDTO(SupplierRequestDTO dto) {
        return existingDto -> SupplierRequestDTO.builder()
                .setId(existingDto.getId())
                .setName(dto.getName() != null ? dto.getName() : existingDto.getName())
                .setCnpj(dto.getCnpj() != null ? dto.getCnpj() : existingDto.getCnpj())
                .setPhone(dto.getPhone() != null ? dto.getPhone() : existingDto.getPhone())
                .setEmail(dto.getEmail() != null ? dto.getEmail() : existingDto.getEmail())
                .setAddress(dto.getAddress() != null ? dto.getAddress() : existingDto.getAddress())
                .build();
    }


}
