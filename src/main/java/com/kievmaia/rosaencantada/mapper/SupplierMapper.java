package com.kievmaia.rosaencantada.mapper;

import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierSummaryDTO;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier requestDTOToEntity(SupplierRequestDTO dto) {
        return Supplier.builder()
                .setId(dto.getId())
                .setName(dto.getName())
                .setCnpj(dto.getCnpj())
                .setPhone(dto.getPhone())
                .setEmail(dto.getEmail())
                .setAddress(dto.getAddress())
                .build();
    }

    public SupplierResponseDTO entityToResponseDTO(Supplier entity) {
        return SupplierResponseDTO.builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setCnpj(entity.getCnpj())
                .setPhone(entity.getPhone())
                .setEmail(entity.getEmail())
                .setAddress(entity.getAddress())
                .setCreatedDate(entity.getCreatedDate())
                .setUpdatedDate(entity.getUpdatedDate())
                .build();
    }

    public SupplierSummaryDTO entityToSummaryDTO(Supplier entity) {
        return SupplierSummaryDTO.builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .build();
    }

    public SupplierRequestDTO toUpdatedDTO(SupplierResponseDTO existingDto, SupplierRequestDTO newDto) {
        return SupplierRequestDTO.builder()
                .setId(existingDto.getId())
                .setName(newDto.getName() != null ? newDto.getName() : existingDto.getName())
                .setCnpj(newDto.getCnpj() != null ? newDto.getCnpj() : existingDto.getCnpj())
                .setPhone(newDto.getPhone() != null ? newDto.getPhone() : existingDto.getPhone())
                .setEmail(newDto.getEmail() != null ? newDto.getEmail() : existingDto.getEmail())
                .setAddress(newDto.getAddress() != null ? newDto.getAddress() : existingDto.getAddress())
                .build();
    }
}

