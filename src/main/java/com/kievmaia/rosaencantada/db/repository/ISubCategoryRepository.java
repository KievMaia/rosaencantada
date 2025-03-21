package com.kievmaia.rosaencantada.db.repository;

import com.kievmaia.rosaencantada.db.entity.Category;
import com.kievmaia.rosaencantada.db.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISubCategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("FROM SubCategory c WHERE unaccent(LOWER(c.name)) = unaccent(LOWER(:categoryName))")
    Optional<Category> findByNameIgnoreCase(String categoryName);
}
