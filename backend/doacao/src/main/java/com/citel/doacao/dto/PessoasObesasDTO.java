package com.citel.doacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoasObesasDTO {

    private String sexo;
    private Integer totalObesos;
    private Integer qtdDoadores;

}
