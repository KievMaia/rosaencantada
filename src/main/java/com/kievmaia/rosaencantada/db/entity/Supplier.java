package com.kievmaia.rosaencantada.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "rosaencantada", name = "fornecedores")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String name;

    @Column(name = "cnpj", length = 20, unique = true)
    private String cnpj;

    @Column(name = "telefone", length = 20)
    private String phone;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "endereco", length = 200)
    private String address;

    @Column(name = "data_criacao", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "data_atualizacao", insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
