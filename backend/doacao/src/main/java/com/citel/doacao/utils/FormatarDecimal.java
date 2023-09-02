package com.citel.doacao.utils;

import java.text.DecimalFormat;

public class FormatarDecimal {

    public static Double decimalParaDuasCasas(Double decimal){
        return Double.valueOf(String.format("%.2f", decimal).replaceAll(",", "."));
    }
}
