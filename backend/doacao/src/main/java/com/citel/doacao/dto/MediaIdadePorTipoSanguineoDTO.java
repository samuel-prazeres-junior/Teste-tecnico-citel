package com.citel.doacao.dto;

import com.citel.doacao.utils.FormatarDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaIdadePorTipoSanguineoDTO {
    private String tipoSanguineo;
    private Double mediaIdade;

    public Double getMediaIdade(){
        return FormatarDecimal.decimalParaDuasCasas(mediaIdade);
    }
}
