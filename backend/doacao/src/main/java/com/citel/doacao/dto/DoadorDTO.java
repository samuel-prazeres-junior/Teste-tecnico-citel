package com.citel.doacao.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoadorDTO {
    private String nome;
    private String cpf;
    private String rg;
    @JsonAlias("data_nasc")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNasc;
    private String sexo;
    private String mae;
    private String pai;
    private String email;
    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    @JsonAlias("telefone_fixo")
    private String telefoneFixo;
    private String celular;
    private Double altura;
    private Double peso;
    @JsonAlias("tipo_sanguineo")
    private String tipoSanguineo;

}
