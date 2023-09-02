package com.citel.doacao.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_telefone")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "telefone_fixo")
    private String telefoneFixo;
    private String celular;
    @ManyToOne
    @JoinColumn(name = "doador_id")
    private DoadorEntity doador;

    public TelefoneEntity(String telefoneFixo, String celular, DoadorEntity doador) {
        this.telefoneFixo = telefoneFixo;
        this.celular = celular;
        this.doador = doador;
    }

    @Override
    public String toString() {
        return "TelefoneEntity{" +
                "id=" + id +
                ", telefoneFixo='" + telefoneFixo + '\'' +
                ", celular='" + celular + '\'' +
                ", doador=" + doador +
                '}';
    }
}
