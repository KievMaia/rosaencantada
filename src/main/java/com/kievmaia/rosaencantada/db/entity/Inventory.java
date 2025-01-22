package com.kievmaia.rosaencantada.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estoque")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_produto", unique = true, nullable = false)
    private Product product;

    @Column(name = "qtd_pecas", nullable = false, precision = 10, scale = 2)
    private BigDecimal stockQuantity;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "data_atualizacao")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "ativo", nullable = false)
    private Boolean active;
}
