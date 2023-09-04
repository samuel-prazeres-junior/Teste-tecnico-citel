package com.citel.doacao.service.impl;

import com.citel.doacao.config.mapper.Mapper;
import com.citel.doacao.domain.entity.DoadorEntity;
import com.citel.doacao.domain.entity.EnderecoEntity;
import com.citel.doacao.domain.entity.TelefoneEntity;
import com.citel.doacao.domain.repository.DoadorRepository;
import com.citel.doacao.domain.repository.EnderecoRepository;
import com.citel.doacao.domain.repository.TelefoneRepository;
import com.citel.doacao.dto.*;
import com.citel.doacao.exception.FilterException;
import com.citel.doacao.exception.RecursosNaoEncontradoException;
import com.citel.doacao.service.DoadorService;
import com.citel.doacao.utils.ListTipoSanguineo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoadorServiceImpl implements DoadorService {

    private final DoadorRepository doadorRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;
    private long soma;

    public List<DoadoresPorEstadoDTO> doadoresPorEstado() {
        List<DoadoresPorEstadoDTO> select = enderecoRepository.qtdPossiveisDoadoresPorEstado();
        if (select.isEmpty()){
            throw new RecursosNaoEncontradoException("Recurso não encontrado");
        }
        return select;

    }

    public List<PercentualPessoasObesasDTO> percentualPessoalObesas() {

        List<PessoasObesasDTO> select = doadorRepository.buscarQuantidadePessoasObesosAgrupadasPorGenero();
        if (!select.isEmpty()) {
            return doadorRepository.buscarQuantidadePessoasObesosAgrupadasPorGenero().stream().map(item -> {
                return PercentualPessoasObesasDTO
                        .builder()
                        .sexo(item.getSexo())
                        .qtdDoadores(item.getQtdDoadores())
                        .totalObesos(item.getTotalObesos())
                        .build();
            }).collect(Collectors.toList());
        }
        throw new RecursosNaoEncontradoException("Recurso não encontrado");
    }
    public List<ImcMedioDTO> calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos() {
        List<ImcMedioDTO> select = doadorRepository.calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos();
        if (select.isEmpty()){
            throw new RecursosNaoEncontradoException("Recurso não encontrado");
        }
        return select;
    }

    public List<MediaIdadePorTipoSanguineoDTO> mediaIdadePorTipoSanguineo() {
        List<MediaIdadePorTipoSanguineoDTO> select = doadorRepository.mediaPorTipoSanguineo();
        if (select.isEmpty()){
            throw new RecursosNaoEncontradoException("Recurso não encontrado");

        }
        return select;
    }

    @Transactional
    public void salvarDoadores(List<DoadorDTO> doadores) {
        doadores.forEach(doadorDTO -> {
            DoadorEntity doadorEntity = doadorRepository.save(Mapper.converterDoadorDTOEmDoadorEntity(doadorDTO));
            EnderecoEntity enderecoEntity = Mapper.instanciarEndereco(doadorDTO.getCep(), doadorDTO.getEndereco(),
                    doadorDTO.getNumero(), doadorDTO.getBairro(), doadorDTO.getCidade(), doadorDTO.getEstado(), doadorEntity);

            TelefoneEntity telefoneEntity = Mapper.instanciarTelefone(doadorDTO.getTelefoneFixo(), doadorDTO.getCelular(), doadorEntity);
            doadorRepository.save(doadorEntity);
            enderecoRepository.save(enderecoEntity);
            telefoneRepository.save(telefoneEntity);
        });
    }

    @Override
    public List<TipoSanguineoESuaQuantidadeDTO> quantidadeDePossiveisDoadoresParaCadaTipoSamgioneo() {
        List<TipoSanguineoESuaQuantidadeDTO> responseTipoSanguineoQuantidade = new ArrayList<>();
        List<TipoSanguineoEQuantidadeDeReceptores> tiposSanguineos = doadorRepository.contarPessoasPorTipoSanguineo();
        if (tiposSanguineos.isEmpty()){
            throw new RecursosNaoEncontradoException("Recurso não encontrado");
        }
        ListTipoSanguineo.tipoSanguineoEseusReceptores().forEach(tabelaTipoSanguineo -> {
           soma = 0;
            tabelaTipoSanguineo.getTiposReceptores().forEach(receptor -> {
                soma += tiposSanguineos.stream()
                        .filter(tipoSanguineoEQuantidadeDeReceptores -> tipoSanguineoEQuantidadeDeReceptores.getTipoSanguineo().equals(receptor)).findFirst().orElseThrow(FilterException::new).getQtdPossiveisDoadores();
            });
            responseTipoSanguineoQuantidade.add(TipoSanguineoESuaQuantidadeDTO.builder().tipoSanguineo(tabelaTipoSanguineo.getTipoSanguineo()).qtdPossiveisDoadores(soma).build());
        });
        return responseTipoSanguineoQuantidade;
}


}
