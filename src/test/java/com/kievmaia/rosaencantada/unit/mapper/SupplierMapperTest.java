package com.kievmaia.rosaencantada.unit.mapper;

import com.kievmaia.rosaencantada.mapper.supplier.SupplierMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.kievmaia.rosaencantada.unit.builder.SupplierBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

class SupplierMapperTest {

    private SupplierMapper supplierMapper;

    @BeforeEach
    void setUp() {
        supplierMapper = new SupplierMapper();
    }

    @Test
    @DisplayName("Should correctly map all fields from SupplierRequestDTO to Supplier entity")
    void shouldMapAllFieldsFromSupplierRequestDTOToSupplierEntity() {
        var dto = getSupplierRequestDTO();

        var result = supplierMapper.requestDTOToEntity(dto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(dto.getName());
        assertThat(result.getCnpj()).isEqualTo(dto.getCnpj());
        assertThat(result.getPhone()).isEqualTo(dto.getPhone());
        assertThat(result.getEmail()).isEqualTo(dto.getEmail());
        assertThat(result.getAddress()).isEqualTo(dto.getAddress());
    }

    @Test
    @DisplayName("Should correctly map all fields from Supplier entity to SupplierResponseDTO")
    void shouldMapAllFieldsFromSupplierEntityToSupplierResponseDTO() {
        var entity = buildSupplierEntity();

        var result = supplierMapper.entityToResponseDTO(entity);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(entity.getId());
        assertThat(result.getName()).isEqualTo(entity.getName());
        assertThat(result.getCnpj()).isEqualTo(entity.getCnpj());
        assertThat(result.getPhone()).isEqualTo(entity.getPhone());
        assertThat(result.getEmail()).isEqualTo(entity.getEmail());
        assertThat(result.getAddress()).isEqualTo(entity.getAddress());
        assertThat(result.getCreatedDate()).isEqualTo(entity.getCreatedDate());
        assertThat(result.getUpdatedDate()).isEqualTo(entity.getUpdatedDate());
    }

    @Test
    @DisplayName("Should correctly map only id and name from Supplier entity to SupplierSummaryDTO")
    void shouldMapIdAndNameFromSupplierEntityToSupplierSummaryDTO() {
        var entity = buildSupplierEntity();

        var result = supplierMapper.entityToSummaryDTO(entity);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(entity.getId());
        assertThat(result.getName()).isEqualTo(entity.getName());
        assertThat(result).hasNoNullFieldsOrProperties();
        assertThat(result).hasAllNullFieldsOrPropertiesExcept("id", "name");
    }

    @Test
    @DisplayName("Should handle null values in SupplierRequestDTO when mapping to Supplier entity")
    void shouldHandleNullValuesInSupplierRequestDTOWhenMappingToSupplierEntity() {
        var dto = buildNullValuesSupplierRequestDTO();

        var result = supplierMapper.requestDTOToEntity(dto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isNull();
        assertThat(result.getCnpj()).isNull();
        assertThat(result.getPhone()).isNull();
        assertThat(result.getEmail()).isNull();
        assertThat(result.getAddress()).isNull();
    }

    @Test
    @DisplayName("Should correctly update only some fields when newDto has a mix of null and non-null values")
    void shouldCorrectlyUpdateOnlySomeFieldsWhenNewDtoHasMixOfNullAndNonNullValues() {
        var existingDto = buildSupplierResponseDTO();

        var newDto = buildSupplierRequestDTOToUpdate();

        var result = supplierMapper.toUpdatedDTO(existingDto, newDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("New Name");
        assertThat(result.getCnpj()).isEqualTo("12345678901234");
        assertThat(result.getPhone()).isEqualTo("9876543210");
        assertThat(result.getEmail()).isEqualTo("existing@email.com");
        assertThat(result.getAddress()).isEqualTo("New Address");
    }

    @Test
    @DisplayName("Should handle special characters in newDto fields")
    void shouldHandleSpecialCharactersInNewDtoFields() {
        var existingDto = buildSupplierResponseDTO();

        var newDto = buildSupplierRequestDTOWithSpecialCharacters();

        var result = supplierMapper.toUpdatedDTO(existingDto, newDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("New Name with Spécial Chàracters!");
        assertThat(result.getCnpj()).isEqualTo("98.765.432/0001-00");
        assertThat(result.getPhone()).isEqualTo("+55 (11) 98765-4321");
        assertThat(result.getEmail()).isEqualTo("new.email+special@domain.com");
        assertThat(result.getAddress()).isEqualTo("Rua das Flôres, nº 123 - Bairro Jardim");
    }
}