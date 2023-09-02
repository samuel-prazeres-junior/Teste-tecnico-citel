package com.citel.doacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TabelaTipoSanguineo {
    private String tipoSanguineo;
    private List<String> tiposReceptores;
    private List<String> tiposDoadores;


}
