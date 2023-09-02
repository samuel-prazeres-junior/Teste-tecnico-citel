package com.citel.doacao.config.mapper;

import com.citel.doacao.domain.entity.DoadorEntity;
import com.citel.doacao.domain.entity.EnderecoEntity;
import com.citel.doacao.domain.entity.TelefoneEntity;
import com.citel.doacao.dto.DoadorDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public static DoadorEntity converterDoadorDTOEmDoadorEntity(DoadorDTO doadorDTO) {

        return DoadorEntity.builder()
                .nome(doadorDTO.getNome())
                .cpf(doadorDTO.getCpf())
                .rg(doadorDTO.getRg())
                .dataNasc(doadorDTO.getDataNasc())
                .sexo(doadorDTO.getSexo())
                .mae(doadorDTO.getMae())
                .pai(doadorDTO.getPai())
                .email(doadorDTO.getEmail())
                .altura(doadorDTO.getAltura())
                .peso(doadorDTO.getPeso())
                .tipoSanguineo(doadorDTO.getTipoSanguineo()).build();
    }

    public static EnderecoEntity instanciarEndereco
            (String cep, String endereco, Integer numero, String bairro, String cidade, String estado, DoadorEntity doador) {
        return EnderecoEntity
                .builder()
                .cep(cep)
                .endereco(endereco)
                .numero(numero)
                .bairro(bairro)
                .cidade(cidade)
                .estado(estado)
                .doador(doador)
                .build();
    }

    public static TelefoneEntity instanciarTelefone(String telefone, String celular, DoadorEntity doador) {
        return TelefoneEntity.builder().telefoneFixo(telefone).celular(celular).doador(doador).build();
    }
}
