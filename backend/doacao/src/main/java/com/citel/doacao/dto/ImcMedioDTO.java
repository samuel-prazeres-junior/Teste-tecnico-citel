package com.citel.doacao.dto;

import com.citel.doacao.utils.FormatarDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImcMedioDTO {

    private Integer faixaIdade;
    private Double mediaImc;

    public Double getMediaImc(){
        return FormatarDecimal.decimalParaDuasCasas(mediaImc);
    }
}

