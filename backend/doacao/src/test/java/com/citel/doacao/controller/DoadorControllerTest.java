package com.citel.doacao.controller;

import com.citel.doacao.exception.FilterException;
import com.citel.doacao.exception.RecursosNaoEncontradoException;
import com.citel.doacao.repositorio.dados.Doadores;
import com.citel.doacao.service.impl.DoadorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.util.StreamUtils.BUFFER_SIZE;

@WebMvcTest(controllers = DoadorController.class)
class DoadorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DoadorServiceImpl service;

    @Test
    void salvarDoadores() {
    }

    @Test
    void doadoresPorEstado() throws Exception {
        when(service.doadoresPorEstado()).thenReturn(Doadores.massaDoadoresPorEstado());
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/buscar-por-estado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }
    @Test
    void doadoresPorEstadoNaoEncontrado() throws Exception {
        when(service.doadoresPorEstado()).thenThrow(RecursosNaoEncontradoException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/buscar-por-estado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }
    @Test
    void calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnosNaoEncontrado() throws Exception {
        when(service.calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos()).thenThrow(RecursosNaoEncontradoException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/calcular-media-imc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }
    @Test
    void mediaIdadePorTipoSanguineoNaoEncontrado() throws Exception {
        when(service.mediaIdadePorTipoSanguineo()).thenThrow(RecursosNaoEncontradoException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/media-por-tipo-sanguineo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }
    @Test
    void qtdReceptoresTipoSanguineoNaoEncontrado() throws Exception {
        when(service.quantidadeDePossiveisDoadoresParaCadaTipoSanguioneo()).thenThrow(RecursosNaoEncontradoException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/quantidade-receptores-por-tipo-sanguineo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }
    @Test
    void qtdReceptoresTipoSanguineoFilterNaoEncontrado() throws Exception {
        when(service.quantidadeDePossiveisDoadoresParaCadaTipoSanguioneo()).thenThrow(FilterException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/quantidade-receptores-por-tipo-sanguineo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isInternalServerError());
    }

//    @Test
//    void doadoresPorEstadoNaoEncontradoTimeOut() throws Exception {
//            when(service.doadoresPorEstado()).thenThrow(new TimeoutException(""));
//        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/buscar-por-estado")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers
//                        .status().isGatewayTimeout());
//    }
    @Test
    void percentualPessoalObesas() throws Exception {
        when(service.doadoresPorEstado()).thenReturn(Doadores.massaDoadoresPorEstado());
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/percentual-pessoas-obesas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }
    @Test
    void calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos() throws Exception {
        when(service.doadoresPorEstado()).thenReturn(Doadores.massaDoadoresPorEstado());
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/calcular-media-imc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }
    @Test
    void mediaIdadePorTipoSanguineo() throws Exception {
        when(service.doadoresPorEstado()).thenReturn(Doadores.massaDoadoresPorEstado());
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/media-por-tipo-sanguineo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }
    @Test
    void qtdReceptoresTipoSanguineo() throws Exception {
        when(service.doadoresPorEstado()).thenReturn(Doadores.massaDoadoresPorEstado());
        mockMvc.perform(MockMvcRequestBuilders.get("/doadores/quantidade-receptores-por-tipo-sanguineo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }
    @Test
    void cadastrarDoadores() throws Exception {
        File file = new File("src/test/resources/data.json");
        byte[] fileContent = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

        mockMvc.perform(multipart("/doadores/")
                .file("file", fileContent))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
    }
    @Test
    void cadastrarDoadoresArquivoNaoEncontrado() throws Exception {
        mockMvc.perform(multipart("/doadores/")
                        .file("file", null))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }


}