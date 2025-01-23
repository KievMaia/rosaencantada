package com.kievmaia.rosaencantada.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "rosaencantada", name = "produtos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "product")
    private Inventory inventory;

    @Column(name = "nome", nullable = false, length = 150)
    private String name;

    @Column(name = "descricao", length = 500)
    private String description;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "data_atualizacao")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "ativo", nullable = false)
    private Boolean active;
}
