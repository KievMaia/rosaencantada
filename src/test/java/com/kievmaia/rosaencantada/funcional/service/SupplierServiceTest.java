package com.kievmaia.rosaencantada.funcional.service;

import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.db.repository.ISupplierRepository;
import com.kievmaia.rosaencantada.mapper.SupplierMapper;
import com.kievmaia.rosaencantada.rest.dto.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.SupplierSummaryDTO;
import com.kievmaia.rosaencantada.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

    @Mock
    private ISupplierRepository repository;

    @Mock
    private SupplierMapper mapper;

    @InjectMocks
    private SupplierService service;

    private SupplierRequestDTO supplierRequestDTO;
    private Supplier supplier;
    private SupplierSummaryDTO supplierSummaryDTO;
    private SupplierResponseDTO supplierResponseDTO;

    @BeforeEach
    void setup() {
        supplierRequestDTO = SupplierRequestDTO.builder()
                .setId(1L)
                .setName("Loja do brás")
                .build();

        supplier = Supplier.builder()
                .setId(1L)
                .setName("Loja do brás")
                .build();

        supplierSummaryDTO = SupplierSummaryDTO.builder()
                .setId(1L)
                .setName("Loja do brás")
                .build();

        supplierResponseDTO = SupplierResponseDTO.builder()
                .setId(1L)
                .setName("Loja do brás")
                .build();
    }

    @Test
    @DisplayName("JUnit test for Given Supplier Save Supplier Then Return Supplier")
    void testGivenSupplier_WhenSaveSupplier_ThenReturnSupplier() {
        when(mapper.requestDTOToEntity(any(SupplierRequestDTO.class))).thenReturn(supplier);
        when(mapper.entityToSummaryDTO(any(Supplier.class))).thenReturn(supplierSummaryDTO);
        when(repository.save(any(Supplier.class))).thenReturn(supplier);

        var savedSupplier = service.save(supplierRequestDTO);

        assertNotNull(savedSupplier);
        assertEquals(supplierSummaryDTO.getId(), savedSupplier.getId());
        assertEquals(supplierSummaryDTO.getName(), savedSupplier.getName());
    }

    @Test
    @DisplayName("JUnit test for Given Supplier When Get Supplier By Id Then Return Supplier")
    void testGivenSupplier_WhenGetSupplierById_ThenReturnSupplier() {
        when(repository.findById(supplier.getId())).thenReturn(Optional.ofNullable(supplier));
        when(mapper.entityToResponseDTO(supplier)).thenReturn(supplierResponseDTO);

        var supplierResponse = service.getSupplier(supplier.getId());

        assertNotNull(supplierResponse);
        assertEquals(supplier.getName(), supplierResponse.getName());
    }

    @Test
    @DisplayName("JUnit test for Given Supplier When Get All Suppliers Then Return List of Suppliers")
    void testGivenSuppliers_WhenGetAllSuppliers_ThenReturnSuppliers() {
        var pageable = PageRequest.of(0, 10);
        var supplierPage = new PageImpl<>(List.of(supplier), pageable, 1);
        when(repository.findAll(any(Pageable.class))).thenReturn(supplierPage);

        when(mapper.entityToResponseDTO(any(Supplier.class))).thenReturn(supplierResponseDTO);

        var response = service.getAllSuppliers(pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getContent().size());
        assertEquals("Loja do brás", response.getContent().get(0).getName());
    }

    @Test
    @DisplayName("JUnit test for Given Supplier When Update Supplier Then Return Updated Supplier")
    void testGivenSupplier_WhenUpdateSupplier_ThenReturnUpdatedSupplier() {
        var updatedRequestDTO = SupplierRequestDTO.builder()
                .setId(supplier.getId())
                .setName("Loja do brás atualizado")
                .build();

        var existingResponseDTO = SupplierResponseDTO.builder()
                .setId(supplier.getId())
                .setName(supplier.getName())
                .build();

        var updatedSupplier = Supplier.builder()
                .setId(supplier.getId())
                .setName(updatedRequestDTO.getName()) // Nome atualizado
                .build();

        var summaryDTO = SupplierSummaryDTO.builder()
                .setId(updatedSupplier.getId())
                .setName(updatedSupplier.getName())
                .build();

        when(repository.findById(supplier.getId())).thenReturn(Optional.of(supplier));

        when(mapper.entityToResponseDTO(supplier)).thenReturn(existingResponseDTO);

        when(mapper.toUpdatedDTO(existingResponseDTO, updatedRequestDTO)).thenReturn(updatedRequestDTO);

        when(mapper.requestDTOToEntity(updatedRequestDTO)).thenReturn(updatedSupplier);
        when(repository.save(updatedSupplier)).thenReturn(updatedSupplier);
        when(mapper.entityToSummaryDTO(updatedSupplier)).thenReturn(summaryDTO);

        var updatedResponse = service.updateSupplier(supplier.getId(), updatedRequestDTO);

        assertNotNull(updatedResponse);
        assertEquals(updatedSupplier.getName(), updatedResponse.getName()); // Nome atualizado
        assertNotEquals(supplier.getName(), updatedResponse.getName()); // O nome mudou
    }

    @Test
    @DisplayName("JUnit test for Given Supplier When Delete Supplier Then Return No Content")
    void testGivenSupplier_WhenDeleteSupplier_ThenReturnNoContent() {
        when(repository.findById(supplier.getId())).thenReturn(Optional.of(supplier));
        service.deleteSupplier(supplier.getId());
        verify(repository, times(1)).deleteById(supplier.getId());
    }
}

