package com.citel.doacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoSanguineoESuaQuantidadeDTO {

    private String tipoSanguineo;
    private Long qtdPossiveisDoadores;
}
