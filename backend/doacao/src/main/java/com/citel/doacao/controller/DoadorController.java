package com.citel.doacao.controller;

import com.citel.doacao.dto.*;
import com.citel.doacao.service.impl.DoadorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doadores")
@RequiredArgsConstructor
public class DoadorController {

    private final DoadorServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarDoadores(@Valid @RequestBody List<DoadorDTO> doadores) {
        service.salvarDoadores(doadores);
    }

    @GetMapping("/buscar-por-estado")
    public List<DoadoresPorEstadoDTO> doadoresPorEstado() {
        return service.doadoresPorEstado();
    }

    @GetMapping("/percentual-pessoas-obesas")
    public List<PercentualPessoasObesasDTO> percentualPessoalObesas() {
        return service.percentualPessoalObesas();
    }

    @GetMapping("/calcular-media-imc")
    public List<ImcMedioDTO> calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos() {
        return service.calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos();
    }

    @GetMapping("/media-por-tipo-sanguineo")
    public List<MediaIdadePorTipoSanguineoDTO> mediaIdadePorTipoSanguineo() {
        return service.mediaIdadePorTipoSanguineo();
    }

    @GetMapping("/quantidade-receptores-por-tipo-sanguineo")
    public List<TipoSanguineoESuaQuantidadeDTO> qtdReceptoresTipoSanguineo(){
        return service.quantidadeDePossiveisDoadoresParaCadaTipoSamgioneo();
    }
}
