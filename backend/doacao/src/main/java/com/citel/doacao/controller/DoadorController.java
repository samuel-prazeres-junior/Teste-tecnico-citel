package com.citel.doacao.controller;

import com.citel.doacao.dto.*;
import com.citel.doacao.service.impl.DoadorServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/doadores/")
@RequiredArgsConstructor
public class DoadorController {

    private final DoadorServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarDoadores(@RequestParam MultipartFile file) {
        try {
            if (file.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Arquivo não encontrado");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = file.getInputStream();
            List<DoadorDTO> doadoresEncontrados = objectMapper.readValue(inputStream, new TypeReference<List<DoadorDTO>>() {});
            service.salvarDoadores(doadoresEncontrados);

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("buscar-por-estado")
    public List<DoadoresPorEstadoDTO> doadoresPorEstado() {
        return service.doadoresPorEstado();
    }

    @GetMapping("percentual-pessoas-obesas")
    public List<PercentualPessoasObesasDTO> percentualPessoalObesas() {
        return service.percentualPessoalObesas();
    }

    @GetMapping("calcular-media-imc")
    public List<ImcMedioDTO> calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos() {
        return service.calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos();
    }

    @GetMapping("media-por-tipo-sanguineo")
    public List<MediaIdadePorTipoSanguineoDTO> mediaIdadePorTipoSanguineo() {
        return service.mediaIdadePorTipoSanguineo();
    }

    @GetMapping("quantidade-receptores-por-tipo-sanguineo")
    public List<TipoSanguineoESuaQuantidadeDTO> qtdReceptoresTipoSanguineo(){
        return service.quantidadeDePossiveisDoadoresParaCadaTipoSanguioneo();
    }
}
