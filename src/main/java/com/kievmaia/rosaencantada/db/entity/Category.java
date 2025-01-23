package com.kievmaia.rosaencantada.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorias")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<SubCategory> subCategories;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    @Column(name = "data_criacao", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "data_atualizacao")
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
