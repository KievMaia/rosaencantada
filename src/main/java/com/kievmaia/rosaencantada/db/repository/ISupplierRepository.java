package com.kievmaia.rosaencantada.db.repository;

import com.kievmaia.rosaencantada.db.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("FROM Supplier s WHERE unaccent(LOWER(s.name)) = unaccent(LOWER(:supplierName))")
    Optional<Supplier> findByNameIgnoreCase(String supplierName);
}
