package com.kievmaia.rosaencantada.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fornecedores")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 20, unique = true)
    private String cnpj;

    @Column(length = 20)
    private String phone;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 200)
    private String address;

    @Column(name = "data_cadastro", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
}
