package com.citel.doacao.repositorio.dados;

import com.citel.doacao.dto.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Doadores {


    public static List<DoadorDTO> massaCadastrarDoadores() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return new ArrayList<>(Arrays.asList(new DoadorDTO("Milena Analu Pires", "775.256.099-50", "44.084.541-5", sdf.parse("23/05/1964"), "Feminino",
                "Isadora Marli", "Noah Severino César Pires", "mmilenaanalupires@keffin.com.br", "39801-678", "Rua Kurt W. Rothe", 675, "Castro Pires",
                "Teófilo Otoni", "MG", "(33) 3611-4613", "(33) 98481-0191", 1.53, 80.0, "O-")
));

    }

    public static List<DoadoresPorEstadoDTO> massaDoadoresPorEstado() {
        return new ArrayList<DoadoresPorEstadoDTO>(Arrays.asList(new DoadoresPorEstadoDTO(8, "AC"), new DoadoresPorEstadoDTO(10, "AL")));
    }
    public static List<PessoasObesasDTO> massaPercentualDoadoresObesosRetornoBanco() {
        return Arrays.asList(new PessoasObesasDTO("Masculino", 33, 149), new PessoasObesasDTO("Feminino", 28, 151));
    }
    public static List<PercentualPessoasObesasDTO> massaPercentualDoadoresObesosRetornoService() {
        return Arrays.asList(new PercentualPessoasObesasDTO("Masculino", 33, 149, 22.15), new PercentualPessoasObesasDTO("Feminino", 28, 151, 18.54));
    }
    public static List<ImcMedioDTO> massaMediaImc() {
        return Arrays.asList(new ImcMedioDTO(20, 24.8), new ImcMedioDTO(30, 25.29));
    }
    public static List<MediaIdadePorTipoSanguineoDTO> massaMediaIdadePorTipoSanguineo() {
        return Arrays.asList(new MediaIdadePorTipoSanguineoDTO("A+", 53.63), new MediaIdadePorTipoSanguineoDTO("A-", 46.17));
    }

    public static List<TipoSanguineoEQuantidadeDeReceptores> massaQuantidadeDoadoresPorTipoSanguineoRetornoBanco() {
        return Arrays.asList(new TipoSanguineoEQuantidadeDeReceptores("A+", 27L), new TipoSanguineoEQuantidadeDeReceptores("A-", 34L),
                new TipoSanguineoEQuantidadeDeReceptores("B+", 28L), new TipoSanguineoEQuantidadeDeReceptores("B-", 29L),
                new TipoSanguineoEQuantidadeDeReceptores("AB+", 18L), new TipoSanguineoEQuantidadeDeReceptores("AB-", 23L),
                new TipoSanguineoEQuantidadeDeReceptores("O+", 24L), new TipoSanguineoEQuantidadeDeReceptores("O-", 30L));
    }


    public static List<TipoSanguineoEQuantidadeDeReceptores> massaQuantidadeDoadoresPorTipoSanguineoRetornoService() {
        return Arrays.asList(new TipoSanguineoEQuantidadeDeReceptores("A+", 115L), new TipoSanguineoEQuantidadeDeReceptores("A-", 64L),
                new TipoSanguineoEQuantidadeDeReceptores("B+", 111L), new TipoSanguineoEQuantidadeDeReceptores("B-", 59L),
                new TipoSanguineoEQuantidadeDeReceptores("AB+", 213L), new TipoSanguineoEQuantidadeDeReceptores("AB-", 116L),
                new TipoSanguineoEQuantidadeDeReceptores("O+", 54L), new TipoSanguineoEQuantidadeDeReceptores("O-", 30L));
    }

    public static List<TipoSanguineoEQuantidadeDeReceptores> quantidadeDePossiveisDoadoresParaCadaTipoSanguioneoFilterException() {
        return Arrays.asList(new TipoSanguineoEQuantidadeDeReceptores("A+",115L), new TipoSanguineoEQuantidadeDeReceptores("A-",64L));
    }


}
