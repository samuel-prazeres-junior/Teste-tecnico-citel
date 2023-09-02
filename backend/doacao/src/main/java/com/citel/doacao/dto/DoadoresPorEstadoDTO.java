package com.citel.doacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoadoresPorEstadoDTO {

    private Integer qtdDoadores;
    private String estado;
}
