package com.citel.doacao.service.impl;

import com.citel.doacao.config.mapper.Mapper;
import com.citel.doacao.domain.repository.DoadorRepository;
import com.citel.doacao.domain.repository.EnderecoRepository;
import com.citel.doacao.domain.repository.TelefoneRepository;
import com.citel.doacao.dto.*;
import com.citel.doacao.exception.FilterException;
import com.citel.doacao.exception.RecursosNaoEncontradoException;
import com.citel.doacao.repositorio.dados.Doadores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoadorServiceImplTest {

    @Mock
    private DoadorRepository doadorRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private TelefoneRepository telefoneRepository;

    private DoadorServiceImpl doadorService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        doadorService = new DoadorServiceImpl(doadorRepository, enderecoRepository, telefoneRepository);
    }
    @Test
    void doadoresPorEstado() {
        when(enderecoRepository.qtdPossiveisDoadoresPorEstado()).thenReturn(Doadores.massaDoadoresPorEstado());
        List<DoadoresPorEstadoDTO> doadoresPorEstado = doadorService.doadoresPorEstado();
        verify(enderecoRepository, times(1)).qtdPossiveisDoadoresPorEstado();
        assertEquals(doadoresPorEstado, Doadores.massaDoadoresPorEstado());

    }
    @Test
    void doadoresPorEstadoEmpty() {
        when(enderecoRepository.qtdPossiveisDoadoresPorEstado()).thenReturn(new ArrayList<>());
        assertThrows(RecursosNaoEncontradoException.class, () -> doadorService.doadoresPorEstado());
        verify(enderecoRepository, times(1)).qtdPossiveisDoadoresPorEstado();
    }
    @Test
    void percentualPessoalObesas() {
        when(doadorRepository.buscarQuantidadePessoasObesosAgrupadasPorGenero()).thenReturn(Doadores.massaPercentualDoadoresObesosRetornoBanco());
        List<PercentualPessoasObesasDTO> percentualPessoasObesas = doadorService.percentualPessoalObesas();
        verify(doadorRepository, times(1)).buscarQuantidadePessoasObesosAgrupadasPorGenero();
        assertEquals(percentualPessoasObesas.get(0).getSexo(), Doadores.massaPercentualDoadoresObesosRetornoService().get(0).getSexo());
        assertEquals(percentualPessoasObesas.get(0).getQtdDoadores(), Doadores.massaPercentualDoadoresObesosRetornoService().get(0).getQtdDoadores());
        assertEquals(percentualPessoasObesas.get(0).getTotalObesos(), Doadores.massaPercentualDoadoresObesosRetornoService().get(0).getTotalObesos());
        assertEquals(percentualPessoasObesas.get(0).getPorcentagemObesos(), Doadores.massaPercentualDoadoresObesosRetornoService().get(0).getPorcentagemObesos());

        assertEquals(percentualPessoasObesas.get(1).getSexo(), Doadores.massaPercentualDoadoresObesosRetornoService().get(1).getSexo());
        assertEquals(percentualPessoasObesas.get(1).getQtdDoadores(), Doadores.massaPercentualDoadoresObesosRetornoService().get(1).getQtdDoadores());
        assertEquals(percentualPessoasObesas.get(1).getTotalObesos(), Doadores.massaPercentualDoadoresObesosRetornoService().get(1).getTotalObesos());
        assertEquals(percentualPessoasObesas.get(1).getPorcentagemObesos(), Doadores.massaPercentualDoadoresObesosRetornoService().get(1).getPorcentagemObesos());
    }
    @Test
    void percentualPessoalObesasEmpty() {
        when(doadorRepository.buscarQuantidadePessoasObesosAgrupadasPorGenero()).thenReturn(new ArrayList<>());
        assertThrows(RecursosNaoEncontradoException.class, () -> doadorService.percentualPessoalObesas());
        verify(doadorRepository, times(1)).buscarQuantidadePessoasObesosAgrupadasPorGenero();
    }

    @Test
    void calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos() {
        when(doadorRepository.calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos()).thenReturn(Doadores.massaMediaImc());
        List<ImcMedioDTO> imcMedio = doadorService.calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos();
        verify(doadorRepository, times(1)).calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos();
        assertEquals(imcMedio, Doadores.massaMediaImc());
    }
    @Test
    void calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnosEmpty() {
        when(doadorRepository.calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos()).thenReturn(new ArrayList<>());
        assertThrows(RecursosNaoEncontradoException.class, () -> doadorService.calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos());
        verify(doadorRepository, times(1)).calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos();
    }
    @Test
    void mediaIdadePorTipoSanguineo() {
        when(doadorRepository.mediaPorTipoSanguineo()).thenReturn(new ArrayList<MediaIdadePorTipoSanguineoDTO>(Doadores.massaMediaIdadePorTipoSanguineo()));
        List<MediaIdadePorTipoSanguineoDTO> mediaIdadePorTipoSanguineo = doadorService.mediaIdadePorTipoSanguineo();
        verify(doadorRepository, times(1)).mediaPorTipoSanguineo();
        assertEquals(mediaIdadePorTipoSanguineo, Doadores.massaMediaIdadePorTipoSanguineo());
    }
    @Test
    void mediaIdadePorTipoSanguineoEmpty() {
        when(doadorRepository.mediaPorTipoSanguineo()).thenReturn(new ArrayList<>());
        assertThrows(RecursosNaoEncontradoException.class, () -> doadorService.mediaIdadePorTipoSanguineo());
        verify(doadorRepository, times(1)).mediaPorTipoSanguineo();
    }
    @Test
    void salvarDoadores() throws ParseException {
        List<DoadorDTO> doadores = Doadores.massaCadastrarDoadores();
        when(doadorRepository.save(any())).thenReturn(Mapper.converterDoadorDTOEmDoadorEntity(doadores.get(0)));
        doadorService.salvarDoadores(doadores);
        verify(doadorRepository, times(1)).save(any());
        verify(enderecoRepository, times(1)).save(any());
        verify(telefoneRepository, times(1)).save(any());
    }
    @Test
    void quantidadeDePossiveisDoadoresParaCadaTipoSanguioneo() {
        when(doadorRepository.contarPessoasPorTipoSanguineo())
                .thenReturn(Doadores.massaQuantidadeDoadoresPorTipoSanguineoRetornoBanco());
        List<TipoSanguineoESuaQuantidadeDTO> resultado = doadorService.quantidadeDePossiveisDoadoresParaCadaTipoSanguioneo();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(resultado.get(0).getTipoSanguineo(), Doadores.massaQuantidadeDoadoresPorTipoSanguineoRetornoService().get(0).getTipoSanguineo());
        assertEquals(resultado.get(0).getQtdPossiveisDoadores(), Doadores.massaQuantidadeDoadoresPorTipoSanguineoRetornoService().get(0).getQtdPossiveisDoadores());
    }
    @Test
    void quantidadeDePossiveisDoadoresParaCadaTipoSanguioneoEmpty() {
        List<TipoSanguineoEQuantidadeDeReceptores> tiposSanguineosMock = new ArrayList<>();
        when(doadorRepository.contarPessoasPorTipoSanguineo()).thenReturn(tiposSanguineosMock);
        assertThrows(RecursosNaoEncontradoException.class, () -> doadorService.quantidadeDePossiveisDoadoresParaCadaTipoSanguioneo());

    }
    @Test
    void quantidadeDePossiveisDoadoresParaCadaTipoSanguioneoFilterException() {
        List<TipoSanguineoEQuantidadeDeReceptores> tiposSanguineosMock = new ArrayList<>();
        when(doadorRepository.contarPessoasPorTipoSanguineo())
                .thenReturn(Doadores.quantidadeDePossiveisDoadoresParaCadaTipoSanguioneoFilterException());
        assertThrows(FilterException.class, () -> doadorService.quantidadeDePossiveisDoadoresParaCadaTipoSanguioneo());

    }
}