package com.citel.doacao.service;

import com.citel.doacao.dto.*;

import java.util.List;

public interface DoadorService {
    List<DoadoresPorEstadoDTO> doadoresPorEstado();
    List<PercentualPessoasObesasDTO> percentualPessoalObesas();
    List<ImcMedioDTO> calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos();
    List<MediaIdadePorTipoSanguineoDTO> mediaIdadePorTipoSanguineo();
    void salvarDoadores(List<DoadorDTO> doadores);

    public List<TipoSanguineoESuaQuantidadeDTO> quantidadeDePossiveisDoadoresParaCadaTipoSamgioneo();
}
