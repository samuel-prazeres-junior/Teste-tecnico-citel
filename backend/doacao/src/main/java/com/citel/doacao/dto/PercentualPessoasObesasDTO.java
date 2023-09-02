package com.citel.doacao.dto;

import com.citel.doacao.utils.FormatarDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PercentualPessoasObesasDTO {

    private String sexo;
    private Integer totalObesos;
    private Integer qtdDoadores;
    private Double porcentagemObesos;

    public Double getPorcentagemObesos(){
        return FormatarDecimal.decimalParaDuasCasas(Double.valueOf(totalObesos) / Double.valueOf(qtdDoadores) * 100);
    }
}
