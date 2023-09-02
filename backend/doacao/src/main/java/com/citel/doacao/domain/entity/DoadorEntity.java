package com.citel.doacao.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_doador")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    @Column(name = "data_nasc")
    private LocalDate dataNasc;
    private String sexo;
    private String mae;
    private String pai;
    private String email;
    private Double altura;
    private Double peso;

    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;


}