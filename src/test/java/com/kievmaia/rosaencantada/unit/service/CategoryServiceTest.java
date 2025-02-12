package com.kievmaia.rosaencantada.unit.service;

import com.kievmaia.rosaencantada.db.entity.Category;
import com.kievmaia.rosaencantada.db.repository.ICategoryRepository;
import com.kievmaia.rosaencantada.mapper.category.CategoryMapper;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import com.kievmaia.rosaencantada.service.CategoryService;
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

import static com.kievmaia.rosaencantada.unit.builder.CategoryBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private ICategoryRepository repository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryService service;

    private CategoryRequestDTO categoryRequestDTO;
    private Category category;
    private CategorySummaryDTO categorySummaryDTO;
    private CategoryResponseDTO categoryResponseDTO;

    @BeforeEach
    void setup() {
        categoryRequestDTO = buildCategoryRequestDTO();
        category = buildCategoryEntity();
        categorySummaryDTO = buildCategorySummaryDTO();
        categoryResponseDTO = buildCategoryResponseDTO();
    }

    @Test
    @DisplayName("JUnit test for Given Category Save Category Then Return Category Summary")
    void testGivenCategory_WhenSaveCategory_ThenReturnCategorySummary() {
        when(mapper.requestDTOToEntity(any(CategoryRequestDTO.class))).thenReturn(category);
        when(mapper.entityToSummaryDTO(any(Category.class))).thenReturn(categorySummaryDTO);
        when(repository.save(any(Category.class))).thenReturn(category);

        var savedSupplier = service.save(categoryRequestDTO);

        assertNotNull(savedSupplier);
        assertEquals(categorySummaryDTO.getId(), savedSupplier.getId());
        assertEquals(categorySummaryDTO.getName(), savedSupplier.getName());
    }

    @Test
    @DisplayName("JUnit test for Given Category When Get Category By Id Then Return Category")
    void testGivenCategory_WhenGetCategoryById_ThenReturnCategory() {
        when(repository.findById(category.getId())).thenReturn(Optional.ofNullable(category));
        when(mapper.entityToResponseDTO(category)).thenReturn(categoryResponseDTO);

        var categoryResponse = service.getCategory(category.getId());

        assertNotNull(categoryResponse);
        assertEquals(category.getName(), categoryResponse.getName());
    }

    @Test
    @DisplayName("JUnit test for Given Category When Get Category By Name Then Return Category")
    void testGivenCategory_WhenGetCategoryByName_ThenReturnCategory() {
        when(repository.findByNameIgnoreCase(category.getName())).thenReturn(Optional.ofNullable(category));
        when(mapper.entityToResponseDTO(category)).thenReturn(categoryResponseDTO);

        var categoryResponse = service.getCategoryByName(category.getName());

        assertNotNull(categoryResponse);
        assertEquals(category.getName(), categoryResponse.getName());
    }

    @Test
    @DisplayName("JUnit test for Given Category When Get All Categories Then Return List of Categories")
    void testGivenSuppliers_WhenGetAllSuppliers_ThenReturnSuppliers() {
        var pageable = PageRequest.of(0, 10);
        var categoryPage = new PageImpl<>(List.of(category), pageable, 1);
        when(repository.findAll(any(Pageable.class))).thenReturn(categoryPage);

        when(mapper.entityToResponseDTO(any(Category.class))).thenReturn(categoryResponseDTO);

        var response = service.getAllCategories(pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getContent().size());
        assertEquals("Categoria", response.getContent().get(0).getName());
    }

    @Test
    @DisplayName("JUnit test for Given Supplier When Update Supplier Then Return Updated Supplier")
    void testGivenSupplier_WhenUpdateSupplier_ThenReturnUpdatedSupplier() {
        var updatedRequestDTO = CategoryRequestDTO.builder()
                .setId(category.getId())
                .setName("Categoria atualizado")
                .build();

        var updatedSupplier = Category.builder()
                .setId(category.getId())
                .setName(updatedRequestDTO.getName())
                .build();

        var summaryDTO = CategorySummaryDTO.builder()
                .setId(updatedSupplier.getId())
                .setName(updatedSupplier.getName())
                .build();

        when(repository.findById(category.getId())).thenReturn(Optional.of(category));


        when(mapper.toUpdatedDTO(any(Category.class), eq(updatedRequestDTO))).thenReturn(updatedSupplier);

        when(repository.save(updatedSupplier)).thenReturn(updatedSupplier);
        when(mapper.entityToSummaryDTO(updatedSupplier)).thenReturn(summaryDTO);

        var updatedResponse = service.updateCategory(category.getId(), updatedRequestDTO);

        assertNotNull(updatedResponse);
        assertEquals(updatedSupplier.getName(), updatedResponse.getName());
        assertNotEquals(category.getName(), updatedResponse.getName());
    }

    @Test
    @DisplayName("JUnit test for Given Supplier When Delete Supplier Then Return No Content")
    void testGivenSupplier_WhenDeleteCategory_ThenReturnNoContent() {
        when(repository.findById(category.getId())).thenReturn(Optional.of(category));
        service.deleteCategory(category.getId());
        verify(repository, times(1)).deleteById(category.getId());
    }

}

