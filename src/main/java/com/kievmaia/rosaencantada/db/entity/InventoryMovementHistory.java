package com.kievmaia.rosaencantada.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historico_movimentacoes")
public class InventoryMovementHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estoque_id", nullable = false)
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "entrada_id")
    private InventoryEntry inventoryEntry;

    @ManyToOne
    @JoinColumn(name = "saida_id")
    private InventoryOut inventoryOut;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "data_movimentacao", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime movementDate;
}

