package com.kievmaia.rosaencantada.db.entity;

import com.kievmaia.rosaencantada.db.enumeration.EntryType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estoque_entrada")
public class InventoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_estoque", nullable = false)
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Supplier supplier;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entrada", nullable = false)
    private EntryType entryType;

    @Column(name = "data_entrada", nullable = false, updatable = false)
    private LocalDateTime entryDate;
}