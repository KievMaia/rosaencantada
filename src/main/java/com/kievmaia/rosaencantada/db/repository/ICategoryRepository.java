package com.kievmaia.rosaencantada.db.repository;

import com.kievmaia.rosaencantada.db.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("FROM Category c WHERE unaccent(LOWER(c.name)) = unaccent(LOWER(:categoryName))")
    Optional<Category> findByNameIgnoreCase(String categoryName);
}
