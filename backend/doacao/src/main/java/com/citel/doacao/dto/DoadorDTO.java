package com.citel.doacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoadorDTO {

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;

    @JsonProperty("data_nasc")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNasc;

    @NotBlank
    private String sexo;

    @NotBlank
    private String mae;

    @NotBlank
    private String pai;

    @NotBlank
    private String email;

    @NotBlank
    private String cep;

    @NotBlank
    private String endereco;

    @Positive
    private Integer numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @JsonProperty("telefone_fixo")
    @NotBlank
    private String telefoneFixo;

    @Min(9)
    private String celular;

    @Positive
    private Double altura;

    @Positive
    private Double peso;

    @NotBlank
    @JsonProperty("tipo_sanguineo")
    private String tipoSanguineo;

}
