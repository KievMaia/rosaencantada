package com.kievmaia.rosaencantada.db.repository;

import com.kievmaia.rosaencantada.db.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
}
