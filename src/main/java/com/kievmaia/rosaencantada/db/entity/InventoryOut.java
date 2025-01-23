package com.kievmaia.rosaencantada.db.entity;

import com.kievmaia.rosaencantada.db.enumeration.ExitType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "rosaencantada", name = "estoque_saida")
public class InventoryOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_estoque", nullable = false)
    private Inventory inventory;

    @Column(name = "quantidade", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_saida", nullable = false)
    private ExitType exitType;

    @Column(name = "data_saida", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime exitDate;
}
