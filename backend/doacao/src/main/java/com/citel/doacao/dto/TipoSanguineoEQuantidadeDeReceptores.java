package com.citel.doacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TipoSanguineoEQuantidadeDeReceptores {
    private String tipoSanguineo;
    private Long qtdPossiveisDoadores;
}
